package com.gft.apifinal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gft.apifinal.domain.Casa;
import com.gft.apifinal.repository.Casas;
import com.gft.apifinal.services.exceptions.CasaExistenteException;
import com.gft.apifinal.services.exceptions.CasaNaoEncontradaException;

@Service
public class CasaService {
		
		@Autowired
		private Casas casas;

		public List<Casa> listar() {
		return casas.findAll();
		}		

		public  Casa salvar(Casa casa) {
			if(casa.getId() != 0L) {
				Optional<Casa> a = casas.findById(casa.getId());
				
				if(a.isPresent()) {
					throw new CasaExistenteException("A casa já existe.");
				}
			}
			return casas.save(casa);
		}
		
		public Casa buscar(Long id) {
			Optional<Casa> casa = casas.findById(id);
			if(!casa.isPresent()) {
				throw new CasaNaoEncontradaException("Não foi possível encontrar a casa.");
			}
			return casa.get();
		}
		
		public Casa buscarNome(String n) {
			try {
				return casas.findByNome(n);
			} catch (Exception e) {
				throw new CasaNaoEncontradaException("Não foi possível encontrar a casa.");
			}
		}
		
		public void excluir(Long id) {
			casas.deleteById(id);
		}
		
		public void atualizar(Casa casa) {
			verExiste(casa);
			casas.save(casa);
		}
		
		private void verExiste(Casa casa) {
			buscar(casa.getId());
		}
		
		public List<Casa> listarNomeASC() {
			return casas.findAll(Sort.by(Sort.Direction.ASC, "nome"));
		}
		
		public List<Casa> listarNomeDESC() {
			return casas.findAll(Sort.by(Sort.Direction.DESC, "nome"));
		}
}