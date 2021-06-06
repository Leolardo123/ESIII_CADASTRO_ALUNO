<%@page import="Dominio.Materia"%>
<%@page import="java.util.List"%>
<script>
    <%List<Materia> handmaterias = (List<Materia>) request.getAttribute("materias");%>
        /* 
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    var dependencias = [{identificador: 0}];
            function get_subdeps(){//adiciona as subdependencias
            }
    function add_dep() {
    dependencias.push({identificador: 0});
            load_dep();
    }
    function rm_dep()
    {
    document
            .querySelectorAll("#deps .rmDep")
            .forEach((el, i) => {
            el.addEventListener("click", () => {
            dependencias.splice(i, 1);
                    load_dep();
            });
            });
    }
    function load_dep()
    {
    document.querySelector('.msg-warning-deps').innerHTML = "";
            let old_field = document.querySelector("#deps");
            let default_field = '<div class="input-group mb-3"><select class="form-control dep-item-select" name="dependencia[]"><option value="0">-</option><%for (Materia materia : handmaterias) {%><option value="<%=materia.getId()%>"><%=materia.getNome()%></option><%}%></select><div class="input-group-prepend"><span type="button" class="rmDep btn btn-danger">Delete</span></div></div>'

            old_field = "";
            if (dependencias.length <= <%=handmaterias.size()%>) {
                    dependencias.forEach((el) => {
                    let identificador = el.identificador;
                            old_field += default_field;
    });
    document.getElementById("deps").innerHTML = old_field;
    addevent_rmDep();
    } else {
                            document.querySelector('.msg-warning-deps').innerHTML = "Não pode adicionar mais dependencias!";
    }
    rm_dep(); 
    }
    function addevent_rmDep() {
                            var listenerItems = document.querySelectorAll(".rmDep");
                            for (var i = 0; i < listenerItems.length; i++) {
                    listenerItems[i].addEventListener("click", () => {
                    rm_dep();
    });
    }
    }
    function addevent_addDep() {
                            var listenerItems = document.querySelectorAll(".addDep");
                            for (var i = 0; i < listenerItems.length; i++) {
                    listenerItems[i].addEventListener("click", () => {
                    add_dep();
    });
    }
    }
    document.querySelector('.dep-item-select').addEventListener('change',function(e){
                            alert(e.target.value);
    })
    window.addEventListener('load', function () {
                            addevent_rmDep();
                            addevent_addDep();
    });
    load_dep();
</script>