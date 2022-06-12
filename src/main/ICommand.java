package main;

public interface ICommand {

    String getKeyword();

    void execute(String parameter);

}
