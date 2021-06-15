/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrasNegocio.implRegras;

import Dao.DAODependentes;
import Dao.DAOMateria;
import Dominio.EntidadeDominio;
import Dominio.Materia;
import java.util.ArrayList;
import java.util.List;
import regrasNegocio.IStrategy;

/**
 *
 * @author 55119
 */
public class ValidarDependencias implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {
        if (entidade instanceof Materia) {
            StringBuilder sb = new StringBuilder();
            Materia materia = (Materia) entidade;

            if(materia.getDependencias()!=null&&materia.getDependencias().size()>0){
                for (Materia dependencia : materia.getDependencias()) {

                    if (dependencia.getId() == 0) {
                        sb.append("Dependencia sem id, verifique se ela existe!");
                    }

                    if(dependencia.getId() == materia.getId()){
                        sb.append("Materia não pode depender dela mesma!");
                    }

                    DAOMateria DAOMat = new DAOMateria();
                    List<EntidadeDominio> listMat = DAOMat.consultar(dependencia);

                    if(listMat!=null&&listMat.size()>0){
                        dependencia = (Materia) listMat.get(0);
                    }else{
                        return "dependencia não foi encontrada!";
                    }

                    if (dependencia.getNome() == null) {
                        sb.append("Falta Nome na Dependencia ou  ela não existe!");
                    }

                    if (dependencia.getDescricao() == null) {
                        sb.append("Falta Descricao na Dependencia!");
                    }

                    if (sb.length() > 0) {
                        sb.insert(0, dependencia.getId() + "-" + dependencia.getNome() + "!");
                    }
                }
            }

            if (sb.length() > 0) {
                return sb.toString();
            }
        } else {
            return "Entidade recebida Inválida, esperava Matéria(Dependencias)";
        }
        return null;
    }
}
