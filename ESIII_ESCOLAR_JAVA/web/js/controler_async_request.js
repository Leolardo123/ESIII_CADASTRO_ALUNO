/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function getClasses(nomeClasse,callback){
    const classes_url  = {
        "aluno":"ListarAluno",
        "professor":"ListarProfessor",
        "curso":"ListarCurso",
        "materia":"ListarMateria"
    };
    
    var request = new XMLHttpRequest();
    
    request.onload = function() {
        if (typeof callback === 'function') callback(request.responseText);
    };
    
    request.open("GET",'/ESIII_ESCOLAR_JAVA/'+classes_url[nomeClasse]+'?operacao=CONSULTAR');
    request.send();
    
    return request.responseText;
}
function getClassId(nomeClasse,id,callback){
    const classeId_url = {
        "aluno":"FormEditarAluno",
        "professor":"FormEditarProfessor",
        "curso":"FormEditarCurso",
        "materia":"FormEditarMateria"
    }

    var request = new XMLHttpRequest();
    
    request.onload = function() {
        if (typeof callback === 'function') callback(request.responseText);
    };
    
    request.open("GET",'/ESIII_ESCOLAR_JAVA/'+classeId_url[nomeClasse]+'?operacao=CONSULTARID&id='+id);
    request.send();
    
    return request.responseText;
}
function getAllDeps(callback){
    var request = new XMLHttpRequest();
    
    request.onload = function() {
        if (typeof callback === 'function') callback(request.responseText);
        if (document.querySelector('#loading_msg')!=null){
            document.querySelector('#loading_msg').innerHTML =  '<div class="alert alert-success"  role="alert">'+
                                                                    'Carregado!'+
                                                                '</div>';
        }
    };
    request.open("GET",'/ESIII_ESCOLAR_JAVA/ListarMateria?operacao=CONSULTARTODASDEP');
    request.send();
    
    return request.responseText;
}

