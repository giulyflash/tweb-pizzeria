function validaReg() {
    var txtUsername = document.getElementById("txtUsername");
    var txtPassword = document.getElementById("txtPassword");
    
    var error = false;
    
    if(txtUsername.value==="") error=true;
    else if(txtPassword.value==="") error=true;
    
    if(error) alert("Uno o più dati sono errati!");
    else {
        document.getElementById("frmReg").submit();
    }
}

function validaLogin() {
    var txtUsername = document.getElementById("txtUsername");
    var txtPassword = document.getElementById("txtPassword");
    
    var error = false;
    
    if(txtUsername.value==="") error=true;
    else if(txtPassword.value==="") error=true;
    
    if(error) alert("Uno o più dati sono errati!");
    else {
        document.getElementById("frmLogin").submit();
    }
}

function aggiungiPrenotazione(){
    var cmbPizza = document.getElementById("cmbPizza");
    var txtQuantita = document.getElementById("txtQuantita");
    var table=document.getElementById("table");
    var rowCount = parseInt(table.rows.length);
    var error=false;
    var i=0;
    
    if (cmbPizza.value==="" || cmbPizza.value==="Scegli la pizza" || txtQuantita.value==="" || txtQuantita.value==="0"){
        alert ("Completare correttamente tutti i campi");
        error=true;
    }
    
    if (!error){
            var num = parseInt(txtQuantita.value); 
            var row = table.insertRow(-1);
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            var element3 = document.createElement("input");
            var element2 = document.createElement("input");
            var element1 = document.createElement("input");
            element1.type = "text";
            element1.name = "txtNome"+rowCount;
            element1.value= cmbPizza.value;
            element1.setAttribute ("class","textbox");
            element1.readOnly=true;
            element2.type = "number";
            element2.name = "txtNum"+rowCount;
            element2.value= num;
            element3.type = "checkbox";
            element3.name = "chkStatus"+rowCount;
            element3.checked="checked";
            element3.readOnly=true;
            cell3.appendChild(element3)
            cell2.appendChild(element2);
            cell1.appendChild(element1);
            cmbPizza.value="Scegli la pizza";
            txtQuantita.value=null;
            while (i<cmbPizza.options.length){
                if (cmbPizza.options[i].value===element1.value) cmbPizza.options[i]=null;
                i++;
            }
    }
}

function confPrenotazione(){
    var txtPrenota=document.getElementById("table");
    var hidden=document.getElementById("rowCount");
    var rowCount = table.rows.length-1;
    var error=false;
    
    if (rowCount<1){
        alert ("Inserisci almeno una prenotazione");
        error=true;
    }
    
    if (!error){
        hidden.value=rowCount;
        document.getElementById("pizze").submit();
    }
}

function aggiungiPizza (){
        var nome=document.getElementById("txtNome");
        var ingr=document.getElementById("txtIngr");
        var prezzo=document.getElementById("txtPrezzo");
        var error=false;
        
        if (nome.value===""||ingr.value===""||prezzo===""){
            alert("Compilare tutti i campi");
            error=true;
        }
        
        if (!error) document.getElementById("insert").submit();
    }
    
function modificaPizza (){
        var nome=document.getElementById("cmbPizza");
        var ingr=document.getElementById("txtIngrU");
        var prezzo=document.getElementById("txtPrezzoU");
        var error=false;
        
        if (nome.value==="Scegli la pizza"||ingr.value===""||prezzo===""){
            alert("Compilare tutti i campi");
            error=true;
        }
        
        if (!error) document.getElementById("update").submit();
    }
    
function cancellaPizza (){
        var nome=document.getElementById("cmbPizzaD");
        var error=false;
        
        if (nome.value==="Scegli la pizza"){
            alert("Comilare tutti i campi");
            error=true;
        }
        
        if (!error) document.getElementById("delete").submit();
}

function makeVisible(){
    var insert=document.getElementById("chkInsert");
    var update=document.getElementById("chkUpdate");
    var cancella=document.getElementById("chkDelete");
    
    if(insert.checked) document.getElementById("inserisci").hidden=false;
    else document.getElementById("inserisci").hidden=true;
    
    if(update.checked) document.getElementById("modifica").hidden=false;
    else document.getElementById("modifica").hidden=true;
    
    if(cancella.checked) document.getElementById("cancella").hidden=false;
    else document.getElementById("cancella").hidden=true;
}

function confermaOrdini(){
    var table=document.getElementById("conferma");
    var rowCount=table.rows.lenght;
    var i=1;
    var errore=true;
    
    while (i<rowCount){
        if ("chkOrdine"+i.checked) errore=false;
    }
    
    if (errore===true) alert ("Seleziona almeno un ordine!");
    else document.getElementById("Ordini").submit();
}

function importa(){
    $.post("Controller", {nome:document.getElementById("cmbPizza").value, action:"import"},
            function(xmlResponse)
            {
               alert("ciao!");
               var xml = $.parseXML(xmlResponse);
               var prezzo = $(xml).find("prezzo").text()
               var ingr = $(xml).find("ingredienti").text()
               document.getElementById("txtIngrU").value=ingr;
               document.getElementById("txtPrezzoU").value=prezzo;
            })
}
