# AccountLoginActivityApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getLoginHistory**](AccountLoginActivityApi.md#getloginhistory) | **POST** /api/account/login-activity/filter |  |



## getLoginHistory

> CustomApiResponsePageLoginAttemptResponse getLoginHistory(searchFilter)



### Example

```ts
import {
  Configuration,
  AccountLoginActivityApi,
} from '';
import type { GetLoginHistoryRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AccountLoginActivityApi(config);

  const body = {
    // SearchFilter
    searchFilter: ...,
  } satisfies GetLoginHistoryRequest;

  try {
    const data = await api.getLoginHistory(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | [SearchFilter](SearchFilter.md) |  | |

### Return type

[**CustomApiResponsePageLoginAttemptResponse**](CustomApiResponsePageLoginAttemptResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)

