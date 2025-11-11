# RoomsManagementApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**_delete**](#_delete) | **DELETE** /api/organization/rooms/{id} | |
|[**create**](#create) | **POST** /api/organization/rooms | |
|[**filter1**](#filter1) | **POST** /api/organization/rooms/filter | |
|[**getRoomById**](#getroombyid) | **GET** /api/organization/rooms/{id} | |
|[**update**](#update) | **PUT** /api/organization/rooms/{id} | |

# **_delete**
> CustomApiResponseVoid _delete()


### Example

```typescript
import {
    RoomsManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new RoomsManagementApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance._delete(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseVoid**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **create**
> CustomApiResponseRoomResponse create(roomRequest)


### Example

```typescript
import {
    RoomsManagementApi,
    Configuration,
    RoomRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new RoomsManagementApi(configuration);

let roomRequest: RoomRequest; //

const { status, data } = await apiInstance.create(
    roomRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **roomRequest** | **RoomRequest**|  | |


### Return type

**CustomApiResponseRoomResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **filter1**
> CustomApiResponsePageRoomResponse filter1(searchFilter)


### Example

```typescript
import {
    RoomsManagementApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new RoomsManagementApi(configuration);

let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filter1(
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |


### Return type

**CustomApiResponsePageRoomResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getRoomById**
> CustomApiResponseRoomResponse getRoomById()


### Example

```typescript
import {
    RoomsManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new RoomsManagementApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.getRoomById(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseRoomResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **update**
> CustomApiResponseRoomResponse update(roomRequest)


### Example

```typescript
import {
    RoomsManagementApi,
    Configuration,
    RoomRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new RoomsManagementApi(configuration);

let id: string; // (default to undefined)
let roomRequest: RoomRequest; //

const { status, data } = await apiInstance.update(
    id,
    roomRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **roomRequest** | **RoomRequest**|  | |
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseRoomResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

