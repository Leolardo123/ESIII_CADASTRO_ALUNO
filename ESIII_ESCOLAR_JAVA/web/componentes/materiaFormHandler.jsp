<%@page import="Dao.DAOMateria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Dao.DAODependentes"%>
<%@page import="Dominio.Materia"%>
<%@page import="Dominio.EntidadeDominio"%>
<%@page import="java.util.List"%>
<script>
    var alldeps        = {};
    var notalloweddeps = {};
    window.addEventListener('load', function () {
            var cbfun   = (materias) => {
                materias = JSON.parse(materias);
                var cbfunalldeps = function (fullDepList) {
                    var temp_alldeps = JSON.parse(fullDepList);
                    for(var i=0;i<temp_alldeps.length;i++){
                        alldeps[temp_alldeps[i]['id']]        = temp_alldeps[i]['dependencias'];
                        alldeps[temp_alldeps[i]['id']]['nome']= temp_alldeps[i]['nome'];
                    }
                    document.querySelector('#addDep').addEventListener('click', function () {
                        add_dep();
                    });
                    addevent_all();
                    get_subdeps();
                };
            getAllDeps(cbfunalldeps);
            };
        getClasses('materia',cbfun);
    });
    //after loadded
        function get_subdeps() {//adiciona as subdependencias, e carrega estas na tela
            var subdeps_html = "";
            notalloweddeps = new Object();
            let temp_notalloweddeps = {};//armazena temporiamente as restrições, para que restrições anteriores sejam revisadas
            document.querySelectorAll(".dep-item-select").forEach((select) => {
                subdeps_html = "";
                if (alldeps[select.value]!==null&&select.value>0) {
                    subdeps_html = '<div class="subdeps-container row"><div class="col-md-12"><h2>Herança</h2></div>';
                    for (const [chave, valor] of Object.entries(alldeps[select.value])) {
                            if(valor['nome']!=null){
                            temp_notalloweddeps[valor['id']] = valor['nome'];
                            subdeps_html += '<div class="col-md-4">' +
                                    '<select name="subdep_select" disabled><option value=' + valor['id'] + '>' + valor['nome'] + '</option></select>' +
                                    '</div>';
                            }
                    }
                    subdeps_html += '</div><br>';
                    if (Object.keys(temp_notalloweddeps).length <= 0)
                        subdeps_html = "";
                    select.parentNode.parentNode.querySelectorAll(".subdeps")[0].innerHTML = subdeps_html;
                } else {
                    select.parentNode.parentNode.querySelectorAll(".subdeps")[0].innerHTML = "";
                }
                Object.assign(notalloweddeps, temp_notalloweddeps);
                if (document.querySelector('input[name="id"]') != null) {//impede que a materia tenha ela mesma como dependencia
                    var matId = document.querySelector('input[name="id"]').value;
                    notalloweddeps[matId] = document.querySelector('input[name="nome_materia"]').value;
                }
                temp_notalloweddeps = {};
            })
            load_options();
        }
        function load_options() {//recarrega as opções da tela de acordo com uma lista de opções não permitidas
            var exists = {};
            document.querySelectorAll(".dep-item-select").forEach((select) => {//remove as não permitidas da tela
                if (notalloweddeps[select.value]) {
                    console.log('selects:'+select.value);
                    select.parentNode.parentNode.parentNode.remove();
                }
                if(exists[select.value]){
                    select.parentNode.parentNode.parentNode.remove();
                }else{
                    exists[select.value] = true;
                }
            })
            document.querySelectorAll(".dep-item-select > option").forEach((option) => {//remove as não permitidas da tela
                if (notalloweddeps[option.value]) {
                    option.remove(option);
                }
            })
            var option = {};
            for (const [chave, valor] of Object.entries(alldeps)) {//recupera as opções removidas que agora são permitidas
                if (!notalloweddeps[chave]) {
                    option = document.createElement('option');
                    option.value     = chave;
                    option.innerHTML = valor["nome"];
                    document.querySelectorAll(".dep-item-select").forEach((select) => {//coloca as permitidas na tela
                        var exists = select.querySelector('option[value="' + chave + '"]');
                        if (exists != null)
                            exists = exists.value
                        if (exists != chave)
                            select.appendChild(option);
                    })
                }
            }
        }
        function add_dep() {
            var removed = Object.keys(notalloweddeps).length;
            var list    = Object.keys(alldeps).length;
            if (document.querySelectorAll(".dep-item-select").length < (list - removed)) {
                let default_field = '<div class="item_dependencia">' +
                        '<div class="input-group mb-3"><select class="form-control dep-item-select" name="dependencia[]">' +
                        '<option value="-1">-</option>' +
                        '</select><div class="input-group-prepend"><span type="button" class="rmDep btn btn-danger">Delete</span></div></div>' +
                        '<div class="subdeps col-sm-12"></div>' +
                        '</div>';

                let div = document.createElement('div');
                div.innerHTML = default_field;
                document.querySelector('#deps').appendChild(div);

                addevent_all();

                load_options();
            } else {
                alert("Não há mais dependencias para adicionar!");
            }
        }
        function addevent_all() {
            var itens = document.querySelectorAll('.dep-item-select');
            for (var i = 0; i < itens.length; i++) {
                itens[i].addEventListener('change', (e) => {
                    if (e.target.value != 0) {
                        get_subdeps();
                    }
                });
            }
            document.querySelectorAll("#deps .rmDep").forEach((el, i) => {
                el.addEventListener("click", (e) => {
                    e.target.parentNode.parentNode.parentNode.remove();
                    get_subdeps();
                });
            });
        }
</script>