package com.util;

import java.io.IOException;  
import java.io.InputStream;  
import java.lang.reflect.Field;
import java.math.BigDecimal;
 
import java.text.SimpleDateFormat;  
import java.util.ArrayList;  
import java.util.Date;
import java.util.Iterator;
import java.util.List;  
//java.lang.ClassCastException: 
//	org.apache.poi.hssf.usermodel.HSSFWorkbook cannot be cast to 
//	org.apache.poi.ss.usermodel.Workbook

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  

  
  
public class ExcelUtil {  
      
    private final static String excel2003L =".xls";    //2003- 版本的excel  
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel  
      
    /** 
     * 描述：获取IO流中的数据，组装成List<List<Object>>对象 
     * @param in,fileName 
     * @return 
     * @throws IOException  
     */  
    public  List<List<Object>> getBankListByExcel(InputStream in,String fileName) throws Exception{  
        List<List<Object>> list = null;  
          
        //创建Excel工作薄  
        Workbook work = this.getWorkbook(in,fileName);  
        if(null == work){  
            throw new Exception("创建Excel工作薄为空！");  
        }  
        Sheet sheet = null;  //页数
        Row row = null;  //行数
        Cell cell = null;  //列数
          
        list = new ArrayList<List<Object>>();  
        //遍历Excel中所有的sheet  
        // 将最大的列数记录下来
        int lastCellNum = 0;
        for (int i = 0; i < work.getNumberOfSheets(); i++) {  
            sheet = work.getSheetAt(i);  
            if(sheet==null){continue;}  
              
            //遍历当前sheet中的所有行  
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {  
                row = sheet.getRow(j);  
                if(row==null||row.getFirstCellNum()==j){continue;}  
                  
                //遍历所有的列  
                List<Object> li = new ArrayList<Object>(); 
                // 比较当前行的列数跟表的最大的列数
                if (j == sheet.getFirstRowNum()) {
                	// 将第一行的列数设为最大
                	lastCellNum = row.getLastCellNum();
				}else {
					lastCellNum = lastCellNum > row.getLastCellNum() ? lastCellNum : row.getLastCellNum(); 
				}
                for (int y = row.getFirstCellNum(); y < lastCellNum; y++) {  
                    cell = row.getCell(y);  
                    li.add(this.getValue(cell));  
                } 
                list.add(li);  
            }  
        }  
 
        return list;  
        
    }  
      
    /** 
     * 描述：根据文件后缀，自适应上传文件的版本  
     * @param inStr,fileName 
     * @return 
     * @throws Exception 
     */  
    public  Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{  
        Workbook wb = null;  
        String fileType = fileName.substring(fileName.lastIndexOf("."));  
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
  //解决excel类型问题，获得数值  
    public  String getValue(Cell cell) {  
        String value = "";  
        if(null==cell){  
            return value;  
        }  
        switch (cell.getCellType()) {  
        //数值型  
        case Cell.CELL_TYPE_NUMERIC:  
            if (HSSFDateUtil.isCellDateFormatted(cell)) {  
                //如果是date类型则 ，获取该cell的date值  
                Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue()); 
				// 根据自己的实际情况，excel表中的时间格式是yyyy-MM-dd HH:mm:ss还是yyyy-MM-dd，或者其他类型
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                // 由于方法的返回值类型为String，这里将Date类型转为String，便于统一返回数据 
                value = format.format(date);;  
            }else {// 纯数字  
                BigDecimal big=new BigDecimal(cell.getNumericCellValue());  
                value = big.toString();  
                //解决1234.0  去掉后面的.0  
                if(null!=value&&!"".equals(value.trim())){  
                     String[] item = value.split("[.]");  
                     if(1<item.length&&"0".equals(item[1])){  
                         value=item[0];  
                     }  
                }  
            }  
            break;  
            //字符串类型   
        case Cell.CELL_TYPE_STRING:  
            value = cell.getStringCellValue().toString();  
            break;  
        // 公式类型  
        case Cell.CELL_TYPE_FORMULA:  
            //读公式计算值  
            value = String.valueOf(cell.getNumericCellValue());  
            if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串  
                value = cell.getStringCellValue().toString();  
            }  
            break;  
        // 布尔类型  
        case Cell.CELL_TYPE_BOOLEAN:  
            value = " "+ cell.getBooleanCellValue();  
            break;   
        default:  
            value = cell.getStringCellValue().toString();  
    }  
    if("null".endsWith(value.trim())){  
        value="";  
    }  
  return value;  
    }  
    
    
    
    /**
     * 导出excel
     * @param headerName （excel列名称）
     * @param headerKey （导出对象属性名）
     * @param sheetName （excel 页签名称）
     * @param dataList （导出的数据）
     * @return
     */
    public static HSSFWorkbook createExcel(String[] headerName, String[] headerKey, String sheetName, List dataList) {
        try {
            if (headerKey.length <= 0) {
                return null;
            }
            if (dataList.size() <= 0) {
                return null;
            }
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet;
            if ((sheetName == null) || (sheetName.equals("")))
                sheet = wb.createSheet("Sheet1");
            else {
                sheet = wb.createSheet(sheetName);
            }
            HSSFRow row = sheet.createRow(0);
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment((short)2);
            HSSFCell cell = null;
            if (headerName.length > 0) {
                for (int i = 0; i < headerName.length; i++) {
                    cell = row.createCell(i);
                    cell.setCellValue(headerName[i]);
                    cell.setCellStyle(style);
                     
                }
            }
            int n = 0;
            HSSFCellStyle contextstyle = wb.createCellStyle();
            contextstyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00_);(#,##0.00)"));
 
            HSSFCellStyle contextstyle1 = wb.createCellStyle();
            HSSFDataFormat format = wb.createDataFormat();
            contextstyle1.setDataFormat(format.getFormat("@"));
 
            HSSFCell cell0 = null;
            HSSFCell cell1 = null;
 
            for (Iterator localIterator = dataList.iterator(); localIterator.hasNext();) {
                Object obj = localIterator.next();
                Field[] fields = obj.getClass().getDeclaredFields();
                row = sheet.createRow(n + 1);
                for (int j = 0; j < headerKey.length; j++) {
                    if (headerName.length <= 0) {
                        cell0 = row.createCell(j);
                        cell0.setCellValue(headerKey[j]);
                        cell0.setCellStyle(style);
                         
                    }
                    for (int i = 0; i < fields.length; i++) {
                        if (fields[i].getName().equals(headerKey[j])) {
                            fields[i].setAccessible(true);
                            if (fields[i].get(obj) == null) {
                                row.createCell(j).setCellValue("");
                                break;
                            }
                            if ((fields[i].get(obj) instanceof Number)) {
                                cell1 = row.createCell(j);
                                cell1.setCellType(0);
                                cell1.setCellStyle(contextstyle);
                                cell1.setCellValue(Double.parseDouble(fields[i].get(obj).toString()));
                                break;
                            }
                            if ("".equals(fields[i].get(obj))) {
                                cell1 = row.createCell(j);
                                cell1.setCellStyle(contextstyle1);
                                row.createCell(j).setCellValue("");
                                cell1.setCellType(1);
                                break;
                            }
                            row.createCell(j).setCellValue(fields[i].get(obj).toString());
                            break;
                        }
 
                    }
                }
                n++;
            }
            for (int i = 0; i < headerKey.length; i++) {
                sheet.setColumnWidth(i, headerKey[i].getBytes().length*2*256);
            }
            HSSFWorkbook localHSSFWorkbook1 = wb;
            return localHSSFWorkbook1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        }
    }
}  

