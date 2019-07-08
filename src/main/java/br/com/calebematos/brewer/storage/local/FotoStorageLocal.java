package br.com.calebematos.brewer.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import br.com.calebematos.brewer.storage.FotoStorage;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

public class FotoStorageLocal implements FotoStorage {

	private static final Logger logger = LoggerFactory.getLogger(FotoStorageLocal.class);

	private Path local;
	private Path localTemporario;

	public FotoStorageLocal() {
		this(getDefault().getPath(System.getenv("HOME"), ".brewerfotos"));
	}

	public FotoStorageLocal(Path local) {
		this.local = local;
		criarPastas();
	}

	@Override
	public String salvarFoto(MultipartFile[] files) {
		String novoNome = null;
		if (files != null && files.length > 0) {

			MultipartFile arquivo = files[0];
			novoNome = renomearNomeFoto(arquivo.getOriginalFilename());
			try {
				arquivo.transferTo(new File(
						this.localTemporario.toAbsolutePath().toString() + getDefault().getSeparator() + novoNome));
			} catch (IOException e) {
				throw new RuntimeException("Erro ao salvar arquivo temporário", e);
			}
		}
		return novoNome;
	}

	@Override
	public byte[] recuperarFotoTemporaria(String nome) {
		try {
			return Files.readAllBytes(this.localTemporario.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao carregar foto temporária.");
		}
	}

	@Override
	public void salvar(String foto) {
		try {
			Files.move(this.localTemporario.resolve(foto), this.local.resolve(foto));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao mover foto para destino final.");
		}

		try {
			Thumbnails.of(this.local.resolve(foto).toString()).size(40, 68).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			throw new RuntimeException("Erro ao gerar thumnail.");
		}

	}

	@Override
	public byte[] recuperarFoto(String nome) {
		try {
			return Files.readAllBytes(this.local.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao carregar foto temporária.");
		}
	}

	@Override
	public byte[] recuperarThumbnail(String foto) {
		return recuperarFoto("thumbnail." + foto);
	}

	private void criarPastas() {
		try {
			Files.createDirectories(local);
			this.localTemporario = getDefault().getPath(local.toString(), "temp");
			Files.createDirectories(localTemporario);

			if (logger.isDebugEnabled()) {
				logger.debug("Pastas criadas para salvar fotos.");
				logger.debug("Pasta defaut " + this.local.toAbsolutePath());
				logger.debug("Pasta temporária " + this.localTemporario.toAbsolutePath());
			}

		} catch (IOException e) {
			throw new RuntimeException("Erro ao criar pastas para salvar fotos.");
		}
	}

	private String renomearNomeFoto(String nome) {
		String novoNome = UUID.randomUUID().toString() + "_" + nome;

		if (logger.isDebugEnabled()) {
			logger.debug("Nome antigo: {}. Novo nome: {}", nome, novoNome);
		}

		return novoNome;
	}
}
