// Data e hora exibido no sistema
function ano(a) {
	$(a).jclock({format: '%Y'});
}

function dataHora() {
    $('#relogio').jclock({format: '%d/%m/%Y %H:%M'});
}

function boasVindasHoras() {
    $('.boas-vindas-horas').jclock({format: '%H:%M'});
}

function boasVindasData() {
    $('.boas-vindas-data').jclock({format: '%a, %d de %B'});
}

$(document).ready(function(){
    ano('.ano-sistema');
});

