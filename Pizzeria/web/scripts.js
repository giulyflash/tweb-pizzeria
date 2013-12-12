function validaReg() {
    var txtUsername = document.getElementById("txtUsername");
    var txtPassword = document.getElementById("txtPassword");
    var cmbRuolo = document.getElementById("cmbRuolo");
    
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
    
    var error=false;
    
    if (cmbPizza.value==="" || txtQuantita.value===""){
        alert ("Completare tutti i campi");
        error=true;
    }
    
    if (!error){
        var row = table.insertRow(-1);
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        cell1.innerHTML = cmbPizza.value;
        cell2.innerHTML = txtQuantita.value;
        //document.getElementById("dati").submit();
    }
}

function confPrenotazione(){
    var txtPrenota=document.getElementById("txtPrenota");
    var error=false;
    
    if (txtPrenota.value===""){
        alert ("Inserisci almeno una prenotazione");
        error=true;
    }
    
    if (!error){
        document.getElementById("aggiungi").submit();
    }
}