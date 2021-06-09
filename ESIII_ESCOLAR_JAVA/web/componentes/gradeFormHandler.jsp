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
    var isFormEditar = document.querySelector('input[name="id"]');
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

    var notalloweddeps = new Object();
    
    <%
        DAOCurso DAOHandCurso = new DAOCurso();
        List<EntidadeDominio> handGradeEntidadeCurso = DAOHandCurso.consultar(null);
        
        for(EntidadeDominio entidadeCursoHandler:handGradeEntidadeCurso){
            Curso handCurso = (Curso)entidadeCursoHandler;%>
            allgra["<%=handCurso.getId()%>"] = {
            <%for(GradeCurso handlerGrade:handCurso.getGradeCurso()){%>
                "<%=handlerGrade.getSemestre()%>":{
                <%for(ItemGrade handlerItemGrade:handlerGrade.getItens()){%>
                        "<%=handlerItemGrade.getChave()%>":{
                            "<%=handlerItemGrade.getMateria().getId()%>":"<%=handlerItemGrade.getMateria().getNome()%>",
                          "<%=handlerItemGrade.getProfessor().getId()%>":"<%=handlerItemGrade.getProfessor().getNome()%>"
                        },
                <%}%>
                },
            <%}%>
            }
            allgra["<%=handCurso.getId()%>"]["duracao"] = "<%=handCurso.getDuracao()%>";
        <%}%>
            
    console.log(alldeps);
    console.log(allgra);
            
    function setSemestres(curso_id){
        var options = "";
        var select  = document.querySelector('select[name="semestre"]');
        var duracao = allgra[curso_id]["duracao"];
        if(document.querySelector('input[name="old_semestre"]')!=null){
            var editar_id = document.querySelector('input[name="old_semestre"]').value
        }
        for(var i=1;i<=duracao;i++){
            if(allgra[curso_id][i]==null){
                options += "<option value='"+i+"' >"+i+"</option>"
            }else if( isFormEditar!==null){
                if(editar_id==i){
                   options += "<option value='"+i+"' class='opcao-cadastrada'>"+i+" (Original)</option>"
                   i=duracao+1;
                }else{
                   options += "<option value='"+i+"' class='opcao-cadastrada' disabled>"+i+" (Cadastrado)</option>"
                }
            }
        }
        select.innerHTML = options;
    }
    
    function setMaterias(){
        var cadastradas = {};
        var semestre    = document.querySelector('select[name="semestre"]').value;
        var curso       = document.querySelector('select[name="curso"]').value;
        var materias    = alldeps;
        cadastradas[curso] = materias
        
        var tempdeps = 0;
        var html = "";
        
        for (var i=1;i<semestre-1;i++) {
            if(allgra[curso][i]!=null){
                for (const [chave, valor] of Object.entries(allgra[curso][i])){
                    console.log(chave)
                    console.log(valor)
                }
            }
        }
    }
    
    function editarGetWarning(){
        var modal_body = document.querySelector('.modal-body');
        var modal_title= document.querySelector('.modal-title');
        var substituir = '{!}Se você continuar a grade existente será removida e substituida por essa<br>'+
                         '{!}Todos os itens da grade anterior serão removidos<br>'+
                         '{?}Continuar com a alteração da grade?'
        var mover      = '{!}Se você continuar essa grade o semestre será recadastrado no semestre vazio<br>'+
                         '{?}Continuar com a alteração da grade?'
        var editar     = '{?}Continuar com a alteração da grade?';
        var select     = document.querySelector('select[name="semestre"]');
        
        const checkEditar   = new RegExp('Cadastrado','g');
        const checkOriginal = new RegExp('Original','g');

        if(checkEditar.test(select.options[select.selectedIndex].text)){
            modal_title.innerHTML = "Substituir Grade";
            modal_body.innerHTML  = substituir;
        }else if(checkOriginal.test(select.options[select.selectedIndex].text)){
            modal_title.innerHTML = "Editar Grade";
            modal_body.innerHTML  = editar;
        }else{
            modal_title.innerHTML = "Mover Grade";
            modal_body.innerHTML  = mover;
        }
    }
    
    window.addEventListener('load', function () {
        document.querySelector('select[name="curso"]').addEventListener('change',()=>{
            setSemestres(e.target.value);
            setMaterias();
        })
    
        document.querySelector('select[name="semestre"]').addEventListener('change',()=>{
            setMaterias();
        })
        
        var modalEditarGrade = document.querySelector('#WarningEditarGrade');
        if(modalEditarGrade!=null){
            document.querySelector('#trigger-WarningEditar').addEventListener('click',()=>{
                editarGetWarning()
            })
        }
        
        setSemestres(document.querySelector('select[name="curso"]').value);
        
        var original_sets     = {}
        var semestre_global   = document.querySelector('select[name="semestre"]').value;
        var curso_global      = document.querySelector('select[name="curso"]').value;

        console.log(allgra[curso_global][semestre_global]);
    });
</script>