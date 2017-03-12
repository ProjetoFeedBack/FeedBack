
package com.feedback.web.command.impl;

import com.feedback.core.IFachada;
import com.feedback.core.impl.controle.Fachada;
import com.feedback.web.command.ICommand;

public abstract class AbstractCommand implements ICommand {

	protected IFachada fachada = new Fachada();

}
