package com.spring.ctx.domain.chapter03.parameters.injection.collections;

import com.spring.ctx.domain.chapter03.parameters.injection.nesting.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollectingBean {

    @Autowired @Qualifier("list")
    List<Song> songList1;

    @Autowired
    List<Song> songList2;

    public void printCollections(){
        System.out.println("@Qualifier(\"list\") : ");
        songList1.forEach(s -> System.out.println(s.getTitle()));

        System.out.println("None @Qualifier(\"list\") : ");
        songList2.forEach(s -> System.out.println(s.getTitle()));
    }
}
