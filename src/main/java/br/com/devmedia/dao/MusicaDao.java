package br.com.devmedia.dao;

import br.com.devmedia.domain.Musica;

import java.util.List;

public interface MusicaDao {

    void salvar(Musica musica);

    List<Musica> recuperarPorPlayListId(long id);

    Musica recuperarPorPlayListIdeMusicaId(long playListId, long musicaId);

    void atualizar(Musica musica);

    void excluir(long id);
}
