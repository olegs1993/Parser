package ru.oleg.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.util.List;

/**
 * Created by Oleg on 29.01.2017.
 */
public class Dom4j {
    private String request="http://query.yahooapis.com/v1/public/yql?format=xml&q=select*from%20yahoo.finance.xchange%20where%20pair%20in%20" +
            "(\"USDEUR\",\"USDUAH\")&env=store://datatables.org/alltableswithkeys";
    private SAXReader saxReader;
    private Document document;
    private List<Node> nodeList;
    Node node;

    @SuppressWarnings("unchecked")
    public Dom4j(){
        SAXReader reader=new SAXReader();
        try {
            document= reader.read(request);
            nodeList=document.selectNodes("query/results/rate");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void getResult(String s){
        if (s.equals("USD/EUR")){
           node=nodeList.get(0);
        }
        else if (s.equals("USD/UAH")){
            node=nodeList.get(1);
        }
        else {
            System.err.println("Error!");
            return;
        }
        System.out.println("Name: "+node.selectSingleNode("Name").getText());
        System.out.println( "Rate: "+node.selectSingleNode("Rate").getText());
        System.out.println( "Date: "+node.selectSingleNode("Date").getText());
        System.out.println( "Ask: "+node.selectSingleNode("Ask").getText());
        System.out.println( "Bid: "+node.selectSingleNode("Bid").getText());
    }

}
