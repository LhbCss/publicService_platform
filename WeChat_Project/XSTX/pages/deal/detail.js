// pages/HD/HD.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    a_id: '',
    a_name: '',
    a_party: '',
    a_lore: '',
    a_formatDate: '',
    a_phone: '',
    a_describe: '',
    a_icon: '',
    a_image: '',
    img_mode: 'scaleToFill'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.setNavigationBarTitle({
      title: '活动详情',
    })
    // 将 globalData 域数据取出并赋值给页面数据
    this.setData({
      a_name: app.globalData.detail_a_name,
      a_lore: app.globalData.detail_a_lore,
      a_image: app.globalData.detail_a_image,
      a_icon: app.globalData.detail_a_icon,
      a_phone: app.globalData.detail_a_phone,
      a_describe: app.globalData.detail_a_describe,
      a_formatDate: app.globalData.detail_a_formatDate,
      a_party: app.globalData.detail_a_party,
      a_id: app.globalData.detail_a_id
    })
  },
  signUp: function(){
    /**
     * 用户报名操作，拿到用户的 openid 与 活动 a_id，向服务器发送异步请求对关系表添加数据
     */
    if(app.globalData.openid == ''){
      // 表明用户尚未登录
      wx.showToast({
        title: '您尚未登录！',
        icon: 'error',
        duration: 2000
      })
      return;
    }
    var that = this;
    wx.request({
      url: 'https://publicservice.mynatapp.cc/pages/signUp',
      data:{
        openid : app.globalData.openid,
        a_id: that.data.a_id
      },
      dataType: 'json',
      success(resp){
        if(resp.data == 'sign up success'){
          // 报名成功，返回上一级目录
          wx.showToast({
            title: '报名成功！',
            icon: 'success',
            duration: 2000
          })
          wx.navigateBack;
        }else{
          // 表明用户重复报名
          wx.showToast({
            title: '请勿重复报名！',
            icon: 'error',
            duration: 2000
          })
          wx.navigateBack;
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

  },
  })