/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function ExcluirModal(classNumber,params){//gera o link para excluir o item, recebe o numero da classe e os parametro para exclusao
    var excluir_url = {
        1:"ExcluirAluno?operacao=EXCLUIR&id=*&endid=*",
        2:"ExcluirProfessor?operacao=EXCLUIR&id=*&endid=*",
        3:"ExcluirCurso?operacao=EXCLUIR&id=*",
        4:"ExcluirMateria?operacao=EXCLUIR&id=*"
    };
    if(params.length==(excluir_url[classNumber].split('*').length-1)){
        for(var i=0;i<params.length;i++){
            excluir_url[classNumber] = excluir_url[classNumber].replace('*',params[i]);
        }
        document.querySelector('#modal-exluir-class-id').href = excluir_url[classNumber];
    }
}

window.addEventListener('load', function () {
    var closeErrMsg = document.querySelectorAll(".closeErrMsg");
    for (var i = 0; i < closeErrMsg.length; i++) {
        closeErrMsg[i].addEventListener('click', function () {
            document.querySelector("#errorModal").style.display = 'none';
        })
    }
});

