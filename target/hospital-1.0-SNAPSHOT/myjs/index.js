var vm = new Vue({
    el:'#logindiv',
    data:{
        userlist:{}
    },
    methods:{
        getLogin:function () {
            var _this = this;
            axios.post("user/login.do",_this.userlist).then(function (response) {
                if (response.data==="ok"){
                    alert("登录成功")
                    location.href = "pages/main.html";
                }else{
                    alert("登录失败")
                }
            })

        }
    }
});
