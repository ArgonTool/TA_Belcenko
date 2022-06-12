package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public interface ICommand extends Serializable {

    String getKeyword();

    void execute(String parameter);

}
