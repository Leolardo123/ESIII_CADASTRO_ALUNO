<%@page import="java.util.ArrayList"%>
<%@page import="Dao.DAODependentes"%>
<%@page import="Dominio.Materia"%>
<%@page import="Dominio.EntidadeDominio"%>
<%@page import="java.util.List"%>
<script>
    <%List<Materia> handmaterias = (List<Materia>) request.getAttribute("materias");
      DAODependentes DAOdep = new DAODependentes();
      for(Materia loadTodos:handmaterias){
          List<EntidadeDominio> todasDependencias = DAOdep.consultarTodos(loadTodos);
          List<Materia> tempTodasDeps = new ArrayList<Materia>();
          if(loadTodos.getDependencias()!=null&&loadTodos.getDependencias().size()>0){
            for(EntidadeDominio entidadedependencia:todasDependencias){
                tempTodasDeps.add((Materia)entidadedependencia);
            }   
          }
          loadTodos.setDependencias(tempTodasDeps);
      }
    %>
    /* 
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    var alldeps = {};
    <%for(Materia handmateria:handmaterias){%>
        alldeps["<%=handmateria.getId()%>"] = {
            <%for(Materia handdependencias:handmateria.getDependencias()){%>
                    "<%=handdependencias.getId()%>":
                    "<%=handdependencias.getNome()%>",
            <%}%>
            };
        alldeps["<%=handmateria.getId()%>"]["nome"] = "<%=handmateria.getNome()%>"
    <%}%>
    var notalloweddeps = {};
    function get_subdeps() {//adiciona as subdependencias, e carrega estas na tela
        var subdeps_html = "";
        notalloweddeps = {};
        let temp_notalloweddeps = {};
        document.querySelectorAll(".dep-item-select").forEach((select)=>{
            if(select.value!="0"){
                subdeps_html = '<div class="subdeps-container row"><div class="col-md-12"><h2>Herança</h2></div>';
                for (const [chave, valor] of Object.entries(alldeps[select.value])){
                    temp_notalloweddeps[chave] = valor;
                    subdeps_html += '<div class="col-md-4">'+
                                        '<select name="subdep_select" disabled><option value='+chave+'>'+valor+'</option></select>'+
                                    '</div>';
                }
                subdeps_html += '</div><br>';
            }
            select.parentNode.parentNode.querySelectorAll(".subdeps")[0].innerHTML = subdeps_html;
            Object.assign(notalloweddeps,temp_notalloweddeps);
            temp_notalloweddeps = {};
        })
        load_options();
    }
    function load_options(){//recarrega as opções da tela de acordo com uma lista de opções não permitidas
        document.querySelectorAll(".dep-item-select > option").forEach((option) => {//remove as não permitidas da tela
            if (notalloweddeps[option.value]) {
                option.remove(option);
            }
        })
        
        var allowed_options_html = "<option value='0'>-</option>";
        for (const [chave, valor] of Object.entries(alldeps)){//recupera as opções removidas que agora são permitidas
            if(!notalloweddeps[chave]){
                allowed_options_html += "<option value='"+chave+"'>"+valor['nome']+"</option>";
            }  
        }
        
        document.querySelectorAll(".dep-item-select").forEach((select)=>{//coloca as permitidas na tela
            select.innerHTML = allowed_options_html;
        })
    }
    function add_dep() {
        if(1){
        let default_field =  '<div class="item_dependencia">'+
                             '<div class="input-group mb-3"><select class="form-control dep-item-select" name="dependencia[]">'+
                             '</select><div class="input-group-prepend"><span type="button" class="rmDep btn btn-danger">Delete</span></div></div>'+
                             '<div class="subdeps col-sm-12"></div>'+
                             '</div>';
                     
                        let div = document.createElement('div');
                        div.innerHTML = default_field;
                        document.querySelector('#deps').appendChild(div);
                        
                        addevent_all();
                        
                        load_options();
        }else{
            alert("Não há mais dependencias para adicionar!");
        }
    }
    function addevent_all() {
        var itens = document.querySelectorAll('.dep-item-select');
        for (var i = 0; i < itens.length; i++) {
            itens[i].addEventListener('change', (e)=>{
                if(e.target.value!=0){
                    get_subdeps();
                }
            });
        }
        document.querySelectorAll("#deps .rmDep").forEach((el, i) => {
            el.addEventListener("click", (e) => {
                e.target.parentNode.parentNode.parentNode.remove();
                get_subdeps();
            });
        });;
    }
    
    window.addEventListener('load', function () {
        document.querySelector('#addDep').addEventListener('click',function(){
            add_dep()
        });
        addevent_all();
    });
</script>