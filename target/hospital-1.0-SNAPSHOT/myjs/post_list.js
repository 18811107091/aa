var vm = new Vue({
    el:"#post",
    data:{
        postlist:{},
        postt:{},
        pageNum:1,
        pageSize:5,
        page:[],
        posts:{},
        post:[],
        entity:{}
    },
    methods:{
        changesize:function(obj){
            this.pageSize=obj;
            this.findAllConn();
        },
        paging:function(obj){
            this.pageNum=obj;
            this.findAllConn()
        },
        findAllConn:function () {
            var _this = this;
            axios.post("../user/postlist.do?pageNum="+_this.pageNum+"&pageSize="+_this.pageSize,_this.postt).then(function (response) {
                _this.pageNum=response.data.currentPage;
                _this.postlist=response.data.list;
                _this.page=response.data;
                _this.pageSize=response.data.pageSize;
            })
        },
        fenpeiquanxian:function (id) {
            var _this = this;
            axios.get("../post/selectmenuByid.do?postid="+id).then(function (response) {
                _this.entity=response.data;
                _this.post=response.data.post;
                _this.posts=response.data.posts;
                test(response.data,id);
                document.getElementById("nodes").style.display="block";
            })
        },
        save:function (postid,ids) {
            var _this=this;
            axios.post("../post/savepost.do?postid="+postid,ids).then(function (response) {
                alert("编辑成功")
                document.getElementById("nodes").style.display="none";
            }).catch(function (err) {
                alert(err)
            })

        }
    }
});
vm.findAllConn();