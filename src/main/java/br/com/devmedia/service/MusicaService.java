package br.com.devmedia.service;

import br.com.devmedia.domain.Musica;
import br.com.devmedia.domain.PlayList;

import java.util.List;

public interface MusicaService {

    void salvar(Musica musica, long playlistId);
    List<Musica>recuperarPorPlayList(long playlistId);
    Musica recuperarPorPlayListIdeMusicaId(long playlistId, long musicId);
    void atualizar(Musica musica, long playlistId);
    void excluir(long playlistId, long musicaId);

}
