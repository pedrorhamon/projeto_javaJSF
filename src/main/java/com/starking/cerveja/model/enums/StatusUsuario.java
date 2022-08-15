package com.starking.cerveja.model.enums;

import com.starking.cerveja.repositories.UsuarioRepository;

public enum StatusUsuario {

	ATIVAR {
		@Override
		public void executar(Long[] codigos, UsuarioRepository usuarioRepository) {
			usuarioRepository.findByCodigoIn(codigos).forEach(u -> u.setAtivo(true));
		}
	},
	DESATIVAR {
		@Override
		public void executar(Long[] codigos, UsuarioRepository usuarioRepository) {
			usuarioRepository.findByCodigoIn(codigos).forEach(u -> u.setAtivo(false));		
		}
	};
	
	public abstract void executar(Long[] codigos, UsuarioRepository usuarioRepository);
}
