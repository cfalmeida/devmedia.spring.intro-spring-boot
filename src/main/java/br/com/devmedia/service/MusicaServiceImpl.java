package br.com.devmedia.service;

import br.com.devmedia.dao.MusicaDao;
import br.com.devmedia.domain.Musica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MusicaServiceImpl implements MusicaService {

    @Autowired
    private PlayListService playListService;
    @Autowired
    private MusicaDao musicaDao;

    @Override
    public void salvar(Musica musica, long playlistId) {
        musica.setPlaylist(playListService.recuperarPorId(playlistId));
        musicaDao.salvar(musica);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Musica> recuperarPorPlayList(long playlistId) {
        return musicaDao.recuperarPorPlayListId(playlistId);
    }

    @Override
    @Transactional(readOnly = true)
    public Musica recuperarPorPlayListIdeMusicaId(long playlistId, long musicId) {
        return musicaDao.recuperarPorPlayListIdeMusicaId(playlistId, musicId);
    }

    @Override
    public void atualizar(Musica musica, long playlistId) {
        musica.setPlaylist(playListService.recuperarPorId(playlistId));
        musicaDao.atualizar(musica);
    }

    @Override
    public void excluir(long playlistId, long musicId) {
        musicaDao.excluir( musicaDao.recuperarPorPlayListIdeMusicaId(playlistId, musicId).getId());
    }
}
