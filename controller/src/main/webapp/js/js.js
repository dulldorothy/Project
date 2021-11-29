$(document).ready(function remove(){
    
    var product = document.getElementById("product"), username;
    username = product.getAttribute("data-prodnumber");
    
    if (!(username == ''))
    {
        document.getElementById("log-reg").remove();
        let tag = document.createElement("a");
        tag.className="list-link";
        tag.href='Controller?page=userpage&command=goToPage';
        var text = document.createTextNode(username);
        tag.appendChild(text);
        var element = document.getElementById("userplaceholder");
        element.appendChild(tag);
        
    }
    
    
});