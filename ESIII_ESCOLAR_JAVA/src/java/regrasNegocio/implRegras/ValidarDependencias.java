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
    public static final String repeat_matdep = "Materia não pode depender dela mesma!";

    @Override
    public String processar(EntidadeDominio entidade) {
        if (entidade instanceof Materia) {
            StringBuilder sb = new StringBuilder();
            Materia materia = (Materia) entidade;

            DAODependentes dao = new DAODependentes();

            List<EntidadeDominio> listDeps;

            if (materia.getId() != 0) {
                listDeps = dao.consultarTodos(materia);
                if(listDeps!=null){
                    List<Materia> dependencias = new ArrayList<Materia>();
                    for(EntidadeDominio dep:listDeps){
                        dependencias.add((Materia)dep);
                    }
                    materia.setDependencias(dependencias);
                }
            }

            for (Materia dependencia : materia.getDependencias()) {
                if (dependencia.getId() == materia.getId()) {
                    return repeat_matdep;
                }
                if (dependencia.getId() == 0) {
                    return "Dependencia sem id, verifique se ela existe!";
                }

                DAOMateria DAOMat = new DAOMateria();
                dependencia = (Materia) DAOMat.consultar(dependencia).get(0);

                if (dependencia.getNome() == null) {
                    sb.append("Falta Nome na Dependencia ou  ela não existe!");
                }

                if (dependencia.getDescricao() == null) {
                    sb.append("Falta Descricao na Dependencia!");
                }

                if (sb.length() > 0) {
                    return sb.toString();
                }
            }

        } else {
            return "Entidade recebida Inválida, esperava Matéria(Dependencias)";
        }
        return null;
    }
}
