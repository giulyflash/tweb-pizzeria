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
    if(txtUsername.value=="") error=true;
    else if(txtPassword.value=="") error=true;
    
    if(error) alert("Uno o più dati sono errati!");
    else {
        document.getElementById("frmReg").submit();
    }
}

function validaLogin() {
    var txtUsername = document.getElementById("txtUsername");
    var txtPassword = document.getElementById("txtPassword");
    
    var error = false;
    
    if(txtUsername.value=="") error=true;
    else if(txtPassword.value=="") error=true;
    
    if(error) alert("Uno o più dati sono errati!");
    else {
        document.getElementById("frmLogin").submit();
    }
}