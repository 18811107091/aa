var vm = new Vue({
    el:'#app',
    data:{
        userlist:{},
        stu:{},
        pageSize: 5,
        pageNum: 1,
        page:{},
        savedept:{depts:[]},
        deptBeans:{},
        depts:[],
        deptid:[],
        entity:{},
        dlist:[{postids:[], deptidss:[]}],
        deptids:[],

    },
    methods: {
        changesize: function (obj) {
            this.pageSize = obj;
            this.findAllConn();
        },
        paging: function (obj) {
            this.pageNum = obj;
            this.findAllConn();
        },
        findAllConn: function () {
            var _this = this;
            axios.post("../user/getUserlist.do?pageSize=" + _this.pageSize + "&pageNum=" + _this.pageNum, _this.stu).then(function (response) {
                console.log(_this.stu)
                console.log(_this.userlist)
                _this.userlist = response.data.list;
                _this.page = response.data;
                _this.pageNum = response.data.currentPage;
                _this.pageSize = response.data.pageSize;
            })
        },
        dept:function (id) {
            var _this = this;
            axios.get("../user/savedeptbyid.do?id="+id).then(function (response) {
                _this.savedept = response.data;
                _this.deptBeans = response.data.deptBeans;
                document.getElementById("userdeptdiv").style.display="block";
            })
        },
        upsept:function () {
            var _this = this;
            console.log(_this.savedept)
           axios.post("../user/upuserdept.do?userid="+_this.savedept.id,_this.savedept.depts).then(function (response) {
                alert("编辑成功!");
                _this.findAllConn();
               document.getElementById("userdeptdiv").style.display="none";
            }).catch(function (err) {
                alert(err);
            });
        },
        uppost:function (id) {
            var _this = this;
            axios.get("../user/getpostbyid.do?id="+id).then(function (response) {
                console.log(response.data)
                _this.entity = response.data;
                _this.dlist = response.data.dlist;
                console.log(_this.entity);
                console.log(_this.dlist);
                document.getElementById("userpostdiv").style.display="block";
            })
        },
        addpost:function () {
            this.entity.dlist = this.dlist;
        var _this = this;
        alert(_this.entity)
        axios.post("../user/addpost.do?ismain="+_this.dlist[0].deptidss,_this.entity).then(function (response) {
            alert("添加成功!");
            _this.findAllConn();
            document.getElementById("userpostdiv").style.display="none";
        }).catch(function (err) {
            alert(err);
        });
        }
    }
});
vm.findAllConn();
