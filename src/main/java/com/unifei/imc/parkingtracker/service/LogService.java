package com.unifei.imc.parkingtracker.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class LogService {

    public void writeInFile(ConsumerRecord<String, String> record){
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        try(OutputStream os = new FileOutputStream("syslog.txt", true)) { // Name file
            Writer wr = new OutputStreamWriter(os); // writer
            BufferedWriter br = new BufferedWriter(wr);
            br.write("Sys-log: " + record.topic() + " - " +  now + " - " + record.key());
            br.newLine();
            br.write("Msg - " + record.value());
            br.newLine();
            br.write("Partition - " + record.partition());
            br.newLine();
            br.write("Offset - " + record.offset());
            br.newLine();
            br.newLine();
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
