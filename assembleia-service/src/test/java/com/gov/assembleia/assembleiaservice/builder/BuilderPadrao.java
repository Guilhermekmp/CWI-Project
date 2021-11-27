package com.gov.assembleia.assembleiaservice.builder;

public abstract class BuilderPadrao<E,T> {

  public E construir()  {
    final E entidade = construirEntidade();
    return persistir(entidade);
  }

  /**
   * Este método deve retornar uma instância da entidade inicializada com os dados
   * padrão para todos os testes.
   *
   * @return entidade construída
   */
  public abstract E construirEntidade();

  /**
   * Este método deve retornar uma instância da entidade DTO inicializada com os dados
   * padrão para todos os testes.
   *
   * @return entidade construída
   */
  public abstract T construirEntidadeDTO();

  /**
   * Este método deve persistir e retornar a entidade recebida no parametro
   * <b>entidade</b>
   *
   * @param entidade entidade
   * @return entidade persistida
   */
  protected abstract E persistir(E entidade);



}
