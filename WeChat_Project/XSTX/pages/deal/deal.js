// pages/deal/deal.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tableData: [],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.setNavigationBarTitle({
      title: '我的报名',
    })
    var that = this;
    // 页面加载时，发送异步请求获取服务器所有活动数据信息
    wx.request({
      url: 'https://publicservice.mynatapp.cc'+
      '/pages/getSignUpInformation?openid='+
      app.globalData.openid,
      method: 'GET',
      success: function(resp){
      console.log(resp.data);
      // 将请求数据赋值至 tableData 内
      that.setData({
        tableData: resp.data
      })
    },
      fail: function(resp){
        wx.showToast({
          title: '获取报名信息失败',
          icon: 'error',
          duration : 1500,
        })}
      })
  },
  toDetail: function(e){
    // 将点击事件所在的行数据写入 globalData 域的数据表格中
    getApp().globalData.detail_a_id = e.currentTarget.dataset.rowdata.a_id;
    getApp().globalData.detail_a_name = e.currentTarget.dataset.rowdata.a_name;
    getApp().globalData.detail_a_lore = e.currentTarget.dataset.rowdata.a_lore;
    getApp().globalData.detail_a_icon = e.currentTarget.dataset.rowdata.a_icon;
    getApp().globalData.detail_a_image = e.currentTarget.dataset.rowdata.a_image;
    getApp().globalData.detail_a_party = e.currentTarget.dataset.rowdata.a_party;
    getApp().globalData.detail_a_phone = e.currentTarget.dataset.rowdata.a_phone;
    getApp().globalData.detail_a_formatDate = e.currentTarget.dataset.rowdata.formatDate;
    getApp().globalData.detail_a_describe = e.currentTarget.dataset.rowdata.a_describe;
    
    // 跳转至活动详情页面
    wx.navigateTo({
      url: '/pages/detail/detail',
    })
  },
  confirmModal: function(e){
    wx.showModal({
        title: '取消报名确认',
        content: '你确定要取消报名该活动吗？',
        success: function(res) {
            if (res.confirm) {
            // 用户点击取消，发送用户 openid 至后台取消报名
            var that = this;
            wx.request({
              url: 'https://publicservice.mynatapp.cc/pages/userSignOutActivity?openid=' + app.globalData.openid + '&a_id=' + e.currentTarget.dataset.aid,
              method: 'GET',
              dataType: 'json',
              success(resp){
                if(resp.data == 'sign out success'){
                  // 报名成功，返回上一级目录
                  wx.showToast({
                    title: '取消报名成功！',
                    icon: 'success',
                    duration: 2000
                  })
                  // 强制页面刷新
                  getCurrentPages()[getCurrentPages().length - 1].onLoad();
                  
                }else{
                  // 表明用户重复报名
                  wx.showToast({
                    title: '取消失败！',
                    icon: 'error',
                    duration: 2000
                  })
                  wx.navigateBack;
                }
              }
            })
            } else if (res.cancel) {
              // 用户点击取消，啥也不干
            }

        }

    })

},
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})