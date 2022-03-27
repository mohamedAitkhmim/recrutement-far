package com.far.recrutement.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.far.recrutement.dao.OptionRepository;
import com.far.recrutement.models.Option;

@Service
public class OptionMetier implements IOptionMetier {

    @Autowired
    OptionRepository optionRepository;

    @Override
    public Option addOption(Option o) {
        return optionRepository.save(o);
    }

    @Override
    public List<Option> listOption() {
        return optionRepository.findAll();
    }

}
