package com.careerit.configurations.factory;

import java.util.List;

public class ParserService {

        private Parser parser;

        public void readAndStoreServicesIntoDB(BankType bankType,String filePath){
            // Logic
            parser = ParserFactory.getParser(bankType);
            List<String> list = parser.parse(filePath);


        }

}
