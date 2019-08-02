package com.lzhiy.sc.common.core.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    private final static String excel2003L =".xls";    //2003- 版本的excel
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel


    /**
     * 导出excel
     * @param file
     * @return
     * @throws Exception
     */
    public static  List<List<Object>> importExcel(MultipartFile file) throws Exception{
        if(file.isEmpty()){
            throw new Exception("文件不存在！");
        }
        InputStream in = file.getInputStream();
        String fileName = file.getOriginalFilename();

        List<List<Object>> list = null;
        //创建Excel工作薄
        Workbook work = getWorkbook(in,fileName);
        if(null == work){
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        Integer headCount = 0;

        list = new ArrayList<List<Object>>();
        //遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if(sheet==null){continue;}

            //遍历当前sheet中的所有行
            for (int j = sheet.getFirstRowNum(); j < sheet.getPhysicalNumberOfRows(); j++) {
                row = sheet.getRow(j);
                int count = row.getFirstCellNum();

                if(j==0){
                    headCount = row.getPhysicalNumberOfCells();
                }

                if(row==null||row.getFirstCellNum()==-1||isRowEmpty(row)){continue;}


                //遍历所有的列
                List<Object> li = new ArrayList<Object>();
                for (int y = row.getFirstCellNum(); y < headCount; y++) {
                    cell = row.getCell(y, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    if(cell!=null){
                        li.add(getCellValue(cell));
                    }
                }
                list.add(li);
            }
        }
//        work.close();
        return list;
    }


    /**
     * 导出excel
     * @param dataList
     * @param filePath
     */
    public static void createExcel(List dataList, String filePath){
        // 创建新的Excel 工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 在Excel工作簿中建一工作表，其名为缺省值
        XSSFSheet sheet = workbook.createSheet();

        for(int i = 0; i < dataList.size(); i++){
            Object addr = dataList.get(i);
            List<Map<String, Object>> filedsInfo = getFiledsInfo(addr);
            XSSFRow row = sheet.createRow(i);
            for (int j = 0; j < filedsInfo.size(); j++){
                Map<String, Object> map = filedsInfo.get(j);
                XSSFCell cell = row.createCell(j);
                // 定义单元格为字符串类型
                cell.setCellType(CellType.STRING);
                if (i == 0){
                    // 在单元格中输入一些内容
                    cell.setCellValue(String.valueOf(map.get("name")));
                }else {
                    // 在单元格中输入一些内容
                    cell.setCellValue(String.valueOf(map.get("value")));
                }
            }
        }

        FileOutputStream fos = null;
        try {
            File file = new File(filePath);
            File fileParent = file.getParentFile();
            if(!fileParent.exists()){
                fileParent.mkdirs();
            }
            file.createNewFile();

            fos = new FileOutputStream(filePath);
            // 把相应的Excel 工作簿存盘
            workbook.write(fos);
            fos.flush();
            // 操作结束，关闭文件
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取属性名数组
     * */
    private static String[] getFiledName(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        for(int i=0;i<fields.length;i++){
            System.out.println(fields[i].getType());
            fieldNames[i]=fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * 根据属性名获取属性值
     * */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
     * */
    private static List<Map<String,Object>> getFiledsInfo(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        List list = new ArrayList();
        Map<String,Object> infoMap=null;
        for(int i=0;i<fields.length;i++){
            infoMap = new HashMap();
            infoMap.put("type", fields[i].getType().toString());
            infoMap.put("name", fields[i].getName());
            infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
            list.add(infoMap);
        }
        return list;
    }

    /**
     * 获取对象的所有属性值，返回一个对象数组
     * */
    public static Object[] getFiledValues(Object o){
        String[] fieldNames=getFiledName(o);
        Object[] value=new Object[fieldNames.length];
        for(int i=0;i<fieldNames.length;i++){
            value[i]=getFieldValueByName(fieldNames[i], o);
        }
        return value;
    }

    public static boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellTypeEnum() != CellType.BLANK)
                return false;
        }
        return true;
    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     * @param inStr,fileName
     * @return
     * @throws Exception
     */
    public static  Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        if(excel2003L.equals(fileType)){
            wb = new HSSFWorkbook(inStr);  //2003-
        }else if(excel2007U.equals(fileType)){
            wb = new XSSFWorkbook(inStr);  //2007+
        }else{
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 描述：对表格中数值进行格式化
     * @param cell
     * @return
     */
    public static  Object getCellValue(Cell cell){
        Object value = null;
        switch (cell.getCellTypeEnum()){
            case NUMERIC:
                value = cell.getNumericCellValue();
                break;
            case STRING:
                value = cell.getStringCellValue();
                break;
            case FORMULA:
                value = cell.getStringCellValue();
                break;
            case BLANK:
                value = "";
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;

            default:
                break;

        }
        return value;
    }
}
