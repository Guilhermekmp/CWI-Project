package com.gov.assembleia.assembleiaservice.service.util;

public class ConstantsUtil {

  public static final Long VALOR_PADRAO_DATA_TERMINO = 1L;

  public static final String ASSOCIADO_PODE_VOTAR = "ABLE_TO_VOTE";
  public static final String ASSOCIADO_NAO_PODE_VOTAR = "UNABLE_TO_VOTE";

  public static final String ERRO_CPF_INVALIDO = "O cpf informado é inválido";
  public static final String ERRO_CPF_JA_VOTOU = "O cpf informado já votou e não pode votar denovo";
  public static final String ERRO_DATA_TERMINO_PASSADO = "Não foi possivel salvar, devido a data termino da sessão ser antes da data de abertura";
  public static final String ERRO_PAUTA_NAO_ENCONTRADA = "Não foi possivel buscar a pauta pelo id informado";
  public static final String ERRO_PAUTA_VOTADA = "A pauta informada, já foi votada";
  public static final String ERRO_SESSAO_NAO_ENCONTRADA = "Não foi possivel buscar a quantidade de votos da sessao pelo id informado";
  public static final String ERRO_VOTACAO_ENCERRADA = "A votação dessa sessão já foi encerrada";


  public static final String MENSAGEM_CONTAGEM_VOTOS = "Nessa sessão, foi contabilizando um total de {totalVotos} votos feitos, e com uma contagem de {totalVotosSim} de votos sim e {totalVotosNao} de votos não.";

  public static final String TOTAL_VOTOS = "{totalVotos}";
  public static final String TOTAL_VOTOS_SIM = "{totalVotosSim}";
  public static final String TOTAL_VOTOS_NAO = "{totalVotosNao}";
  private ConstantsUtil(){}
}
