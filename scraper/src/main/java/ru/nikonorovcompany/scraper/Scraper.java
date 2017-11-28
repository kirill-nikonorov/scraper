package ru.nikonorovcompany.scraper;

import ru.nikonorovcompany.pojo.InfoRequest;
import ru.nikonorovcompany.pojo.Instruction;
import ru.nikonorovcompany.service.InfoRequestCreator;
import ru.nikonorovcompany.service.InfoRequestsPrinter;
import ru.nikonorovcompany.service.InstructionCreator;

import java.io.IOException;
import java.util.List;

public class Scraper {
    public static void main(String[] args) throws IOException {

        Instruction instruction = new InstructionCreator(args).createInstruction();

        List<InfoRequest> list = new InfoRequestCreator(instruction).createInfoRequests();

        new InfoRequestsPrinter(list).print();

    }

}
