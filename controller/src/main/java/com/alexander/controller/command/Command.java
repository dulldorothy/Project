package com.alexander.controller.command;



import com.alexander.controller.exeptions.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
     Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException;
}
