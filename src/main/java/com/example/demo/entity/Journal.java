package com.example.demo.entity;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class Journal {

    @Id
    private ObjectId id;
    private String header;
    private String content;
    private LocalDateTime date;

//    public ObjectId getId() {
//        return id;
//    }
//
//    public void setId(ObjectId id) {
//        this.id = id;
//    }
//
//    public String getHeader() {
//        return header;
//    }
//
//    public void setHeader(String header) {
//        this.header = header;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }

//    @Override
//    public String toString() {
//        return "Journal{" +
//                "id='" + id + '\'' +
//                ", header='" + header + '\'' +
//                ", content='" + content + '\'' +
//                ", date=" + date +
//                '}';
//    }
}
