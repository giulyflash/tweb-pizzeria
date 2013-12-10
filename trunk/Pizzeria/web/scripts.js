function validaReg() {
    //var txtNome = document.getElementById("txtNome");
    //var txtCognome = document.getElementById("txtCognome");
    //var txtDataNasc = document.getElementById("txtDataNasc");
    //var rdbSesso = document.getElementById("rdbSesso");
    var txtUsername = document.getElementById("txtUsername");
    var txtPassword = document.getElementById("txtPassword");
    var cmbRuolo = document.getElementById("cmbRuolo");
    
    var error = false;
    
    //if(txtNome.value=="") error=true;
    //else if(txtCognome.value=="") error=true;
    //else if(txtDataNasc.value=="") error=true;
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
    var error=false;
    
    if (cmbPizza.value==="" || txtQuantita.value===""){
        alert ("Completare tutti i campi");
        error=true;
    }
    
    if (!error){
        var txtPrenota=document.getElementById("txtPrenota");
        txtPrenota.value=txtPrenota.value+cmbPizza.value+ " " + txtQuantita.value + "\n";
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
        document.getElementById("dati").action();
    }
}