
dates = ["20130901", "20130902", "20130903", "20130904", "20130905", "20130906", "20130907", "20130908", "20130909", "20130910", "20130911", "20130912", "20130913", "201309014", "20130915", "20130916", "20130917", "20130918", "20130919", "20130920", "20130921", "20130922", "20130923", "20130924"]

headers = ["stats.timers.services.pub-api.prod.wl.call.SKUsByProductGet.mean", "stats.timers.services.pub-api.prod.wl.call.accountAddressDelete.mean", "stats.timers.services.pub-api.prod.wl.call.accountAddressGetCountryStateGroups.mean", "stats.timers.services.pub-api.prod.wl.call.accountAddressSave.mean", "stats.timers.services.pub-api.prod.wl.call.accountAddressesGet.mean", "stats.timers.services.pub-api.prod.wl.call.accountCreate.mean", "stats.timers.services.pub-api.prod.wl.call.accountGet.mean", "stats.timers.services.pub-api.prod.wl.call.accountLogin.mean", "stats.timers.services.pub-api.prod.wl.call.accountOrderHistoryDetailGet.mean", "stats.timers.services.pub-api.prod.wl.call.accountOrderHistoryGet.mean", "stats.timers.services.pub-api.prod.wl.call.accountTenderCreate.mean", "stats.timers.services.pub-api.prod.wl.call.accountTendersGet.mean", "stats.timers.services.pub-api.prod.wl.call.accountUpdate.mean", "stats.timers.services.pub-api.prod.wl.call.cartGet.mean", "stats.timers.services.pub-api.prod.wl.call.cartItemDelete.mean", "stats.timers.services.pub-api.prod.wl.call.cartItemInsert.mean", "stats.timers.services.pub-api.prod.wl.call.cartItemUpdate.mean", "stats.timers.services.pub-api.prod.wl.call.categoryAllGet.mean", "stats.timers.services.pub-api.prod.wl.call.checkoutAccountTenderIDSet.mean", "stats.timers.services.pub-api.prod.wl.call.checkoutFulfillmentSystemCodeReset.mean", "stats.timers.services.pub-api.prod.wl.call.checkoutFulfillmentSystemCodeSet.mean", "stats.timers.services.pub-api.prod.wl.call.checkoutGiftCardSet.mean", "stats.timers.services.pub-api.prod.wl.call.checkoutPayPalPaymentSet.mean", "stats.timers.services.pub-api.prod.wl.call.checkoutPlaceOrder.mean", "stats.timers.services.pub-api.prod.wl.call.checkoutShippingAddressSet.mean", "stats.timers.services.pub-api.prod.wl.call.checkoutShippingMethodIDSet.mean", "stats.timers.services.pub-api.prod.wl.call.checkoutShippingMethodsGet.mean", "stats.timers.services.pub-api.prod.wl.call.checkoutStart.mean", "stats.timers.services.pub-api.prod.wl.call.checkoutStatusGet.mean", "stats.timers.services.pub-api.prod.wl.call.currencyDictionaryGet.mean", "stats.timers.services.pub-api.prod.wl.call.guestAccountCreate.mean", "stats.timers.services.pub-api.prod.wl.call.productBrowse.mean", "stats.timers.services.pub-api.prod.wl.call.productSearch.mean", "stats.timers.services.pub-api.prod.wl.call.wishListGet.mean", "stats.timers.services.pub-api.prod.wl.call.wishListInsert.mean", "stats.timers.services.pub-api.prod.wl.call.wishListItemDelete.mean", "stats.timers.services.pub-api.prod.wl.call.wishListItemInsert.mean", " "]

n = 0

def url_path(headers)
    
    for element in headers
          
        localpaths = headers[element]       
        break
    end
  
  localpaths

end    
   
def url_dates(dates,n)
     
     while n != data.size
     
           n += 1 
                 
     end     
    
   return n

end

dstpaths = url_path(headers)

p dstpaths


#       path = "/render/?width=712&height=382&_salt=1379439659.569&from=00%3A00_" + dates[0] + "&until=23%3A59_" + date[0] +"&target=" + element.to_s + "&rawData=true&format=json"
    
#       path.run(path)          





