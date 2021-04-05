package com.careerit.configurations.factory;

import java.util.HashMap;
import java.util.Map;

public class ParserFactory {

        private static Map<BankType,Parser> map;
        static{
                map = new HashMap<>();
                map.put(BankType.BOFA,new BofaParser());
                map.put(BankType.CITY,new CityParaser());
                map.put(BankType.JPM,new JPMParser());
                map.put(BankType.ICICI,new ICICIParser());
        }

        public static Parser getParser(BankType bankType){
            Parser parser = map.get(bankType);
            if(parser == null)
                throw new IllegalArgumentException("We don't have support for bank :"+bankType.toString());
            return parser;
        }

}
