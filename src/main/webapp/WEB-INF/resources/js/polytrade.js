/**
 * @author hedi brahim <hedi.brahim@gmail.com>
 * version: 1.0.0
 * https://github.com/hedibrahim/polytrade/
 */

function enMillimes(value) {
    var v = value * 1.18;
    return v.toFixed(3).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
}

function actionFormatter(value, row, index) {
    return [
        '<a class="inventaire" href="javascript:void(0)" title="Fiche Commercial">',
        '<span class="glyphicon glyphicon-list-alt"></span>',
        '</a>'
    ].join('');
}

window.actionEvents = {
    'click .inventaire': function (e, value, row, index) {
        //alert('You click inventaire icon, row: ' + JSON.stringify(row.article.reference));		
        //alert('You click inventaire icon, row: ' + JSON.stringify(row));
        window.open('invs_jasper/' + row.id, '_self', false);
        //console.log(value, row, index);
        //console.log(row.article);
    }
};

$(function () {
    //document.getElementById("eventsTable").focus();
    $(".search").append('<span class="glyphicon glyphicon-search"></span>');
    $("div.search > input").focus();
//$(".search").focus();
    /* add the span inside search div with append box*/
});

$(document)
        .ready(function () {
//document.getElementById("eventsTable").focus();
//document.getElementsByClassName("search").focus();
            //$('.ui.sidebar').sidebar({context: $('.bottom.segment')}).sidebar('attach events', '.menu .item');
            $("div.search > input").focus();
        });