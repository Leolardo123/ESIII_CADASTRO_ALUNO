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
    var allgra = {};
    <%
        DAOCurso DAOcur = new DAOCurso();
        List<EntidadeDominio> entidadeCursos = DAOcur.consultar(null);
        Curso cursoHandler = new Curso();

        for (EntidadeDominio entidadeCurso :  entidadeCursos){
            cursoHandler = (Curso) entidadeCurso;
            %>
                allgra["<%=cursoHandler.getId()%>"] = {
            <%
            for (GradeCurso gradeHandler : cursoHandler.getGradeCurso()){%>
                "<%=gradeHandler.getSemestre()%>":"<%=gradeHandler.getId()%>",
            <%}%> 
            }
            allgra["<%=cursoHandler.getId()%>"]["duracao"] = <%=cursoHandler.getDuracao()%>
            
    <%  }%>   
    function setSemestres(curso_id){
        var option;
        var options = "";
        var select  = document.querySelector('select[name="semestre"]');
        var duracao = allgra[curso_id]["duracao"];
        for(var i=1;i<=duracao;i++){
            if(allgra[curso_id][i]==null){
                option   = "<option value='"+i+"'>"+i+"</option>"
                options += option;
            }
        }
        select.innerHTML = options;
    }
//    function editarMigrar(){
//        var ifeditar = document.querySelectorAll('input[name="id"]');
//        var modal = document.querySelectorAll('[aria-labelledby="modalWarningEditarGrade"]');
//        if(ifeditar!=null){
//            modal.classList.add('show');
//        }
//    }
    window.addEventListener('load', function () {
        document.querySelector('select[name="curso"]').addEventListener('change',(e)=>{
            setSemestres(e.target.value);
            editarMigrar(e.target.value);
        })
        setSemestres(document.querySelector('select[name="curso"]').value);
    });
</script>