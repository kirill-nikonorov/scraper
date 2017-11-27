package scraper;

import pojo.InfoRequest;
import service.InfoRequestCreator;
import pojo.Instruction;
import service.InfoRequestsPrinter;
import service.InstructionCreator;

import java.util.List;

public class Scraper {
    public static void main(String[] args) {



        Instruction instruction =new  InstructionCreator(args).createInstruction();

        List<InfoRequest> list = new InfoRequestCreator(instruction).createInfoRequests();

        new InfoRequestsPrinter(list).print();

    }

}
