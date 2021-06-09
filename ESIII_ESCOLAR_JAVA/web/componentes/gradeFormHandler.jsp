<%@page import="Dominio.ItemGrade"%>
<%@page import="Dominio.Curso"%>
<%@page import="Dao.DAOCurso"%>
<%@page import="Dominio.GradeCurso"%>
<%@page import="Dao.DAOMateria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Dao.DAODependentes"%>
<%@page import="Dominio.Materia"%>
<%@page import="Dominio.EntidadeDominio"%>
<%@page import="java.util.List"%>
<script>  
    var allgra   = {};
    
    <%
        DAOMateria DAOmat = new DAOMateria();
        List<EntidadeDominio> entidadehandmaterias = (List<EntidadeDominio>) DAOmat.consultar(null);
        List<Materia> handmaterias = new ArrayList<Materia>();
        if(entidadehandmaterias!=null){
            for (EntidadeDominio entidadeMateria : entidadehandmaterias) {
                handmaterias.add((Materia) entidadeMateria);
            }
        }
        DAODependentes DAOdep = new DAODependentes();
        
        if(handmaterias!=null){
            for (Materia loadTodos : handmaterias) {
                List<EntidadeDominio> todasDependencias = DAOdep.consultarTodos(loadTodos);
                List<Materia> tempTodasDeps = new ArrayList<Materia>();
                if (loadTodos.getDependencias() != null && loadTodos.getDependencias().size() > 0) {
                    for (EntidadeDominio entidadedependencia : todasDependencias) {
                        tempTodasDeps.add((Materia) entidadedependencia);
                    }
                }
                loadTodos.setDependencias(tempTodasDeps);
            }
        }
    %>
        
    var alldeps = {};
    <%for (Materia handmateria : handmaterias) {%>
    alldeps["<%=handmateria.getId()%>"] = {
    <%for (Materia handdependencias : handmateria.getDependencias()) {%>
        "<%=handdependencias.getId()%>":
                "<%=handdependencias.getNome()%>",
    <%}%>
    };
    alldeps["<%=handmateria.getId()%>"]["nome"] = "<%=handmateria.getNome()%>"
    <%}%>
    var alloweddeps = {};
    var semestre_trocado = false;
    var isFormEditar     = document.querySelector('input[name="id"]');
    
    <%
        DAOCurso DAOHandCurso = new DAOCurso();
        List<EntidadeDominio> handGradeEntidadeCurso = DAOHandCurso.consultar(null);
        
        for(EntidadeDominio entidadeCursoHandler:handGradeEntidadeCurso){
            Curso handCurso = (Curso)entidadeCursoHandler;%>
            allgra["<%=handCurso.getId()%>"] = {
            <%for(GradeCurso handlerGrade:handCurso.getGradeCurso()){%>
                "<%=handlerGrade.getSemestre()%>":{
                <%for(ItemGrade handlerItemGrade:handlerGrade.getItens()){%>
                        "<%=handlerItemGrade.getId()%>":"<%=handlerItemGrade.getMateria().getId()%>",
                <%}%>
                },
            <%}%>
            }
            allgra["<%=handCurso.getId()%>"]["duracao"] = "<%=handCurso.getDuracao()%>";
        <%}%>
            
    console.log(allgra);
    console.log(alldeps);
    
    function setSemestres(curso_id){
        var options = "";
        var select  = document.querySelector('select[name="semestre"]');
        var duracao = allgra[curso_id]["duracao"];
        for(var i=1;i<=duracao;i++){
            if(allgra[curso_id][i]==null){
                options += "<option value='"+i+"' >"+i+"</option>"
            }else if( isFormEditar!==null){
                options += "<option value='"+i+"' class='opcao-cadastrada'>"+i+" (Cadastrado)</option>"
            }
        }
        select.innerHTML = options;
    }
    
    function setMaterias(){
        var cadastradas = {};
        var semestre    = document.querySelector('select[name="semestre"]').value;
        var curso       = document.querySelector('select[name="curso"]').value;
        var materias    = [];
        
        for (var i=0;i<semestre;i++) {
   
        }
        cadastradas[curso] = materias
        var tempdeps = 0;
        for (const [chave, valor] of Object.entries(alldeps)) {
            
        }
    }
    
    window.addEventListener('load', function () {
        document.querySelector('select[name="curso"]').addEventListener('change',()=>{
            setSemestres(e.target.value);
            //setMaterias();
        })
    
        document.querySelector('select[name="semestre"]').addEventListener('change',()=>{
            //setMaterias();
        })
        
        setSemestres(document.querySelector('select[name="curso"]').value);
    });
</script>