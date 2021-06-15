/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrasNegocio.implRegras;

import Dao.DAOMateria;
import Dominio.EntidadeDominio;
import Dominio.Materia;
import java.util.List;
import regrasNegocio.IStrategy;

/**
 *
 * @author 55119
 */
public class ValidarMateria implements IStrategy {

    int max_carga_horaria = 9999;
    int min_carga_horaria = 1;

    @Override
    public String processar(EntidadeDominio entidade) {
        if (entidade == null) {
            return "Falha ao receber materia!";
        }
        if (entidade instanceof Materia) {
            StringBuilder sb = new StringBuilder();
            Materia materia = (Materia) entidade;

            DAOMateria DAOmat = new DAOMateria();

            List<EntidadeDominio> listMateria = DAOmat.consultar(materia);

            if (listMateria != null && listMateria.size() > 0) {
                Materia tempMateria = (Materia) listMateria.get(0);

                if (materia.getId() != 0) {
                    if (tempMateria != null && tempMateria.getId() != 0) {
                        materia.setId(tempMateria.getId());
                    } else {
                        sb.append("Materia " + materia.getId() + " não foi encontrada!");
                    }
                } else {
                    if (tempMateria != null && tempMateria.getNome().equals(materia.getNome())) {
                       sb.append("Materia com mesmo nome ("+materia.getNome()+") já existe!");
                    }
                }
            }

            if (materia.getDependencias() != null && materia.getDependencias().size() > 0) {
                ValidarDependencias valDep = new ValidarDependencias();
                String msgDep = valDep.processar(materia);

                if (msgDep != null) {
                    sb.append(msgDep);
                }
            }

            if (materia.getNome() == null) {
                sb.append("Falta Nome na Materia!");
            }
            if (materia.getDescricao() == null) {
                sb.append("Falta Descricao na Materia!");
            }

            if (materia.getCarga_horaria() < min_carga_horaria) {
                sb.append("Carga horária deve ser maior que " + (min_carga_horaria - 1) + "!");
            }

            if (materia.getCarga_horaria() > max_carga_horaria) {
                sb.append("Carga horária deve ser menor que " + max_carga_horaria + "!");
            }

            if (sb.length() > 0) {
                return sb.toString();
            }
        } else {
            return "Entidade recebida Inválida, esperava Matéria";
        }
        return null;
    }
}
