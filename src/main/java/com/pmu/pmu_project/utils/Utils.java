package com.pmu.pmu_project.utils;

import com.pmu.pmu_project.exceptions.InvalidDataRequestException;
import com.pmu.pmu_project.models.dto.PartantDto;

import java.text.SimpleDateFormat;
import java.util.*;

public class Utils {

    public static Date convertStringToDate(String d){
        try{
            SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.ENGLISH);
            return formatter.parse(d);
        }catch (Exception e){
            throw  new InvalidDataRequestException(Constants.DATE_INVALID);
        }
    }

    public static boolean checkIfNumbersOfPartantsIsValid(List<PartantDto> partantList){

        List<Integer> list = new ArrayList<>(partantList.stream().map(PartantDto::getNumero).toList());
        // On trie la liste par ordre croissant
        Collections.sort(list);

        Set<Integer> uniqueNumbers = new HashSet<>();

        for (int i = 0; i < list.size() - 1; i++) {
            int n = list.get(i);
            // On vérifie les doublons
            if (uniqueNumbers.contains(n)) {
                return false;
            } else {
                uniqueNumbers.add(n);
            }

            // Vérifiez si la différence entre les nombres consécutifs est supérieure à 1
            // Verifier s'il y a un trou dans la liste
            if (list.get(i + 1) - n > 1) {
                return false;
            }
        }

        // Vérifier les doublons pour le dernier élément
        return !uniqueNumbers.contains(list.get(list.size() - 1));
    }

}
