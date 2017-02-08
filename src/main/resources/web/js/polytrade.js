/**
 * @author hedi brahim <hedi.brahim@gmail.com>
 * version: 1.0.0
 * https://github.com/hedibrahim/polytrade/
 */

function enMillimes(value) {
    //var v = value * 1.18;
    return value.toFixed(3).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
}




function detailFormatter(value, row, index) {
    //html.push('<p><b>' + key + ':</b> ' + value + '</p>');

    return['<form class="form-horizontal">',
        '<div class="form-group">',
        '<div class="col-sm-6">',
        '<label for="numero">Numero</label>',
        '<span> </span>',
        '<input type="email" class="form-control col-sm-5" id="numero" placeholder="' + row.numero + '" readonly>',
        '</div>',
        '</div>',
        '</form>'].join('');
}


function articleActions(value, row, index) {
    return [
        '<a id="fiche_article" href="javascript:void(0)" title="Fiche Article">',
        '<span class="glyphicon glyphicon-list-alt"></span>',
        '</a>'
    ].join('');
}

window.articleEvents = {
    'click #fiche_article': function (e, value, row, index) {
        //alert('You click inventaire icon, row: ' + JSON.stringify(row.article.reference));		
        window.open('fiche_article/' + row.id, '_self', false);
        //console.log(row.article);
    }
};

function clientActions(value, row, index) {
    return [
        '<a id="fiche_client" href="javascript:void(0)" title="Fiche Client">',
        '<span class="glyphicon glyphicon-list-alt"></span>',
        '</a>'
    ].join('');
}

window.clientEvents = {
    'click #fiche_client': function (e, value, row, index) {
        //alert('You click inventaire icon, row: ' + JSON.stringify(row.article.reference));		
        window.open('fiche_client/' + row.id, '_self', false);
        //console.log(row.article);
    }
};

function fournisseurActions(value, row, index) {
    return [
        '<a id="fiche_fournisseur" href="javascript:void(0)" title="Fiche Fournisseur">',
        '<span class="glyphicon glyphicon-list-alt"></span>',
        '</a>'
    ].join('');
}

window.fournisseurEvents = {
    'click #fiche_fournisseur': function (e, value, row, index) {
        //alert('You click inventaire icon, row: ' + JSON.stringify(row.article.reference));		
        window.open('fiche_fournisseur/' + row.id, '_self', false);
        //console.log(row.article);
    }
};

function familleActions(value, row, index) {
    return [
        '<a id="edit_famille" href="javascript:void(0)" title="Modifier">',
        '<span class="glyphicon glyphicon-edit"></span>',
        '</a>',
        '<a id="fiche_famille" class="ml10" href="javascript:void(0)" title="Fiche">',
        '<span class="glyphicon glyphicon-list-alt text-success"></span>',
        '</a>',
        '<!--a id="remove_famille" class="ml10" title="Supprimer" data-toggle="modal" href="#myModal">',
        '<span class="glyphicon glyphicon-remove text-danger"></span>',
        '</a-->'
    ].join('');
}

/*
$("#65").click(function () {
    $.get("update_date_inventaire", {id: $('#mod_fml_id').val(), date: $('#mod_fml_date > input').val()});
    $('#modal_edit_famille').modal('hide');
    //$('#lstfamilles').bootstrapTable('refresh', {silent: true});
});
*/

window.familleEvents = {
    'click #edit_famille': function (e, value, row, index) {
        //$('#modal_edit_famille').find('.modal-title').text('Details Famille')
        //$('#mod_fml_date').datetimepicker({defaultDate: "05/15/2016",format: "DD/MM/YYYY"});
        $('#mod_fml_id').val(row.id);
        $('#mod_fml_designation>input').val(row.designation);
        $('#mod_fml_date>input').val(row.date);
        $('#modal_edit_famille').modal();
        //alert('You click edit icon, row: ' + JSON.stringify(row));
        //console.log(value, row, index);
    },
    'click #fiche_famille': function (e, value, row, index) {
        window.open('fiche_famille/' + row.id, '_self', false);
    },
    'click #remove_famille': function (e, value, row, index) {
        //alert('You click remove icon, row: ' + JSON.stringify(row));
        console.log(value, row, index);
    }
};

$("#mod_fml_btn_save").click(function () {
    $.get("update_date_inventaire", {id: $('#mod_fml_id').val(), date: $('#mod_fml_date > input').val()});
    $('#modal_edit_famille').modal('hide');
    //$('#lstfamilles').bootstrapTable('refresh', {silent: true});
});

$('#modal_edit_famille').on('hidden.bs.modal', function (e) {
    $('#lstfamilles').bootstrapTable('refresh', {silent: true});
    $('#lstfamilles').bootstrapTable('refresh', {silent: true});
});

function dateActions(value, row, index)
{
    /*
     if(value !== null){
     var curr_date = value.getDate();
     var curr_month = value.getMonth() + 1; //Months are zero based
     var curr_year = value.getFullYear();
     }else{
     value = '';
     }
     */
    return [
        '<div class="col-xs-4">',
        '<div class="input-group input-group-sm">',
        '<input type="text" class="form-control" value="' + value + '">',
        '<span class="input-group-btn">',
        '<button class="btn btn-default save" type="button">',
        '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>',
        '</button>',
        '</span>',
        '</div><!-- /input-group -->',
        '</div><!-- /.col-xs-4 -->'
    ].join('');
}

window.dateEvents = {
    'click .save': function (e, value, row, index) {
        //    window.open('fiche_stock','_self', false);
        //$(".datepicker").datetimepicker({format: "DD-MM-YYYY HH:mm:ss"});
        /*
         console.log(row, row.date);
         console.log(row, value);
         value = '15/01/2017';
         console.log(row, value);
         */
        //document.getElementById("mytext").value = "My value";   
        //$(this).parent().prev().val('15/01/2017');
        //$('#lstfamilles').bootstrapTable('updateRow', {index: index, row: {date: '15/01/2017'}});
        $('#lstfamilles').bootstrapTable('updateByUniqueId', {id: row.id, row: {date: $(this).parent().prev().val()}});
        //$('#lstfamilles').bootstrapTable('updateCell', {index: index, field: 'date', value: '15/01/2017', reinit: true});        
        $.get("update_date_inventaire", {id: row.id, date: row.date});
    }
};

$(document).ready(function () {
    //document.getElementById("eventsTable").focus();
    //document.getElementsByClassName("search").focus();
    //$('.ui.sidebar').sidebar({context: $('.bottom.segment')}).sidebar('attach events', '.menu .item');
    $(".search").append('<span class="glyphicon glyphicon-search"></span>');
    $("div.search > input").focus();
    //document.getElementById("demo").innerHTML = new Date("2015-03-25T12:00:00-06:00");
    //$(".datepicker").datetimepicker({format: "DD-MM-YYYY HH:mm:ss"});
    $('#mod_fml_date').datetimepicker({format: "DD/MM/YYYY"});    
});

/*
 function rowStyle(row, index) {
 var classes = ['active', 'success', 'info', 'warning', 'danger'];
 console.log(row,row.quantite);
 if(parseInt(row.quantite) == 0){
 return { classes : 'danger' };
 }
 return { classes : 'success'};
 }
 */

