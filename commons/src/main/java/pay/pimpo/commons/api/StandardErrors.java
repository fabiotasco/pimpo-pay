
package pay.pimpo.commons.api;

import pay.pimpo.commons.builders.ErrorBuilder;

public class StandardErrors {

	// Auth errors
	public static final Error UNAUTHORIZED_ACCESS
		= new ErrorBuilder().setCode("AUT-0401").setMessage("Acesso não autorizado!").build();

	// User errors
	public static final Error USER_EXISTS
		= new ErrorBuilder().setCode("USR-0001").setMessage("Usuário já cadastrado!").build();

	public static final Error USER_NOT_FOUND
		= new ErrorBuilder().setCode("USR-0002").setMessage("Usuário não encontrado!").build();

	// Account errors
	public static final Error ACTIVE_ACCOUNT_NUMBER_NOT_UNIQUE
		= new ErrorBuilder().setCode("ACT-0001").setMessage("Conta já existente!").build();

	public static final Error ACCOUNT_NUMBER_STATUS_NOT_FOUND
		= new ErrorBuilder().setCode("ACT-0002").setMessage("Conta inexistente!").build();

	public static final Error ACCOUNT_STATUS_NOT_FOUND
		= new ErrorBuilder().setCode("ACT-0003").setMessage("Conta não localizada!").build();

	public static final Error ACCOUNT_CONTRACT_NOT_FOUND
		= new ErrorBuilder().setCode("ACT-0004").setMessage("Contrato não localizado!").build();

	public static final Error INVALID_DOCUMENT_FORMAT
		= new ErrorBuilder().setCode("ACT-0005").setMessage("Formato de documento inválido!").build();

	public static final Error INVALID_PHONE_FORMAT
		= new ErrorBuilder().setCode("ACT-0006").setMessage("Formato de telefone inválido!").build();

	public static final Error NETWORK_OPERATOR_NOT_FOUND
		= new ErrorBuilder().setCode("ACT-0007").setMessage("Operadora não localizada!").build();

	public static final Error ACCOUNT_PLAN_NOT_FOUND
		= new ErrorBuilder().setCode("ACT-0008").setMessage("Plano não localizado!").build();

	public static final Error ACCOUNT_NOT_FOUND
		= new ErrorBuilder().setCode("ACT-0009").setMessage("Conta inexistente!").build();

	public static final Error ACCOUNT_NOT_ENROLLED_ON_PLAN
		= new ErrorBuilder().setCode("ACT-0010").setMessage("Plano não contratado!").build();

	public static final Error ACTIVE_ACCOUNT_NUMBER_NOT_FOUND
		= new ErrorBuilder().setCode("ACT-0011").setMessage("Número não localizado!").build();

	public static final Error ACCOUNT_NOT_ACTIVE
		= new ErrorBuilder().setCode("ACT-0012").setMessage("Conta inativa!").build();

	// Transaction errors
	public static final Error TRANSACTION_NOT_FOUND
		= new ErrorBuilder().setCode("TRX-0001").setMessage("Operação não autorizada!").build();

	public static final Error INVALID_TRANSACTION_MERCHANT_DTO_DATA
		= new ErrorBuilder().setCode("TRX-0002").setMessage("Dados de loja incorretos!").build();

	public static final Error INSUFFICIENT_FUNDS
		= new ErrorBuilder().setCode("TRX-0003").setMessage("Saldo insuficiente!").build();

	public static final Error TRANSACTION_NOT_CANCELLABLE
		= new ErrorBuilder().setCode("TRX-0004").setMessage("Operação não permitida!").build();

	public static final Error TRANSACTION_NOT_CANCELLABLE_BY_USER
		= new ErrorBuilder().setCode("TRX-0005").setMessage("Operação não permitida!").build();

	public static final Error TRANSACTION_ALREADY_CANCELLED
		= new ErrorBuilder().setCode("TRX-0006").setMessage("A transação já está cancelada!").build();

	public static final Error TRANSACTION_BETWEEN_SAME_ACCOUNT_NOT_ALLOWED
		= new ErrorBuilder().setCode("TRX-0007").setMessage("Operação não permitida!").build();

	// Environment errors
	public static final Error MICROSERVICE_NOT_FOUND
		= new ErrorBuilder().setCode("ENV-0001").setMessage("Serviço não encontrado!").build();

	// General errors
	public static final Error INTERNAL_SERVER_ERROR
		= new ErrorBuilder().setCode("GEN-0001").setMessage("Ocorreu um erro!").build();

	public static final Error RESOURCE_NOT_FOUND
		= new ErrorBuilder().setCode("GEN-0002").setMessage("Recurso não encontrado!").build();

}
