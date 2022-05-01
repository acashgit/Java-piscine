package edu.school21.tanks.services;

import edu.school21.tanks.objects.Tanks;
import org.springframework.stereotype.Component;

@Component
public interface StatService {
    void saveStat(Tanks first, Tanks second);
    String send1(Tanks me);
    String send2(Tanks enemy);
}
