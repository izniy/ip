package bearbot.parser;

import bearbot.commands.*;
import bearbot.exceptions.BearBotException;
import bearbot.tasks.TaskList;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static Command parse(String input, TaskList taskList) throws BearBotException {
        String[] words = input.split(" ", 2);
        String commandWord = words[0];

        switch (commandWord) {
            case "list":
                return new ListCommand(taskList);
            case "mark":
                return new MarkCommand(taskList, Integer.parseInt(words[1]) - 1);
            case "unmark":
                return new UnmarkCommand(taskList, Integer.parseInt(words[1]) - 1);
            case "delete":
                return new DeleteCommand(taskList, Integer.parseInt(words[1]) - 1);
            case "todo":
                return new AddCommand(taskList, words[1]); // Todo doesn't need a date
            case "deadline":
                return parseDeadline(words[1], taskList);
            case "event":
                return parseEvent(words[1], taskList);
            case "find":
                if (words.length < 2) {
                    System.out.println("Please provide a keyword to search for.");
                    return null;
                }
                return new FindCommand(taskList, words[1]);
            default:
                throw new BearBotException("Unknown command: " + commandWord);
        }
    }

    private static Command parseDeadline(String args, TaskList taskList) throws BearBotException {
        String[] parts = args.split(" /by ", 2);
        if (parts.length < 2) {
            throw new BearBotException("Deadline must be in format: deadline <description> /by <date>");
        }

        try {
            LocalDate date = LocalDate.parse(parts[1]); //
            return new AddCommand(taskList, parts[0], date);
        } catch (Exception e) {
            throw new BearBotException("Invalid date format! Use yyyy-mm-dd.");
        }
    }

    private static Command parseEvent(String args, TaskList taskList) throws BearBotException {
        String regex = "(.+) /from (.+) /to (.+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(args);

        if (!matcher.matches()) {
            throw new BearBotException("Event must be in format: event <description> /from <start> /to <end>");
        }

        try {
            LocalDate startDate = LocalDate.parse(matcher.group(2));
            LocalDate endDate = LocalDate.parse(matcher.group(3));
            return new AddCommand(taskList, matcher.group(1), startDate, endDate);
        } catch (Exception e) {
            throw new BearBotException("Invalid date format! Use yyyy-mm-dd.");
        }
    }
}
