# PatientManagementApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**checkPatientExistence**](#checkpatientexistence) | **GET** /api/patients/check | |
|[**createAllergy**](#createallergy) | **POST** /api/patients/{patientId}/allergies | |
|[**createEmergencyContact**](#createemergencycontact) | **POST** /api/patients/{patientId}/emergency-contacts | |
|[**createPatient**](#createpatient) | **POST** /api/patients | |
|[**deleteAllergy**](#deleteallergy) | **DELETE** /api/patients/allergies/{allergyId} | |
|[**deleteEmergencyContact**](#deleteemergencycontact) | **DELETE** /api/patients/emergency-contacts/{contactId} | |
|[**deletePatient**](#deletepatient) | **DELETE** /api/patients/{patientId} | |
|[**filter**](#filter) | **POST** /api/patients/filter | |
|[**filterAllergies**](#filterallergies) | **POST** /api/patients/{patientId}/allergies/filter | |
|[**filterEmergencyContacts**](#filteremergencycontacts) | **POST** /api/patients/{patientId}/emergency-contacts/filter | |
|[**getPatientById**](#getpatientbyid) | **GET** /api/patients/{patientId} | |
|[**updateAllergy**](#updateallergy) | **PUT** /api/patients/allergies/{allergyId} | |
|[**updateEmergencyContact**](#updateemergencycontact) | **PUT** /api/patients/emergency-contacts/{contactId} | |
|[**updatePatient**](#updatepatient) | **PUT** /api/patients/{patientId} | |

# **checkPatientExistence**
> CustomApiResponseBoolean checkPatientExistence()


### Example

```typescript
import {
    PatientManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new PatientManagementApi(configuration);

let phone: string; // (optional) (default to undefined)
let email: string; // (optional) (default to undefined)

const { status, data } = await apiInstance.checkPatientExistence(
    phone,
    email
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **phone** | [**string**] |  | (optional) defaults to undefined|
| **email** | [**string**] |  | (optional) defaults to undefined|


### Return type

**CustomApiResponseBoolean**

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

# **createAllergy**
> CustomApiResponseAllergyResponse createAllergy(allergyRequest)


### Example

```typescript
import {
    PatientManagementApi,
    Configuration,
    AllergyRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new PatientManagementApi(configuration);

let patientId: string; // (default to undefined)
let allergyRequest: AllergyRequest; //

const { status, data } = await apiInstance.createAllergy(
    patientId,
    allergyRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **allergyRequest** | **AllergyRequest**|  | |
| **patientId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseAllergyResponse**

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

# **createEmergencyContact**
> CustomApiResponseEmergencyContactResponse createEmergencyContact(emergencyContactRequest)


### Example

```typescript
import {
    PatientManagementApi,
    Configuration,
    EmergencyContactRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new PatientManagementApi(configuration);

let patientId: string; // (default to undefined)
let emergencyContactRequest: EmergencyContactRequest; //

const { status, data } = await apiInstance.createEmergencyContact(
    patientId,
    emergencyContactRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **emergencyContactRequest** | **EmergencyContactRequest**|  | |
| **patientId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseEmergencyContactResponse**

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

# **createPatient**
> CustomApiResponsePatientResponse createPatient(patientRequest)


### Example

```typescript
import {
    PatientManagementApi,
    Configuration,
    PatientRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new PatientManagementApi(configuration);

let patientRequest: PatientRequest; //

const { status, data } = await apiInstance.createPatient(
    patientRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **patientRequest** | **PatientRequest**|  | |


### Return type

**CustomApiResponsePatientResponse**

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

# **deleteAllergy**
> CustomApiResponseVoid deleteAllergy()


### Example

```typescript
import {
    PatientManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new PatientManagementApi(configuration);

let allergyId: string; // (default to undefined)

const { status, data } = await apiInstance.deleteAllergy(
    allergyId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **allergyId** | [**string**] |  | defaults to undefined|


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

# **deleteEmergencyContact**
> CustomApiResponseVoid deleteEmergencyContact()


### Example

```typescript
import {
    PatientManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new PatientManagementApi(configuration);

let contactId: string; // (default to undefined)

const { status, data } = await apiInstance.deleteEmergencyContact(
    contactId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **contactId** | [**string**] |  | defaults to undefined|


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

# **deletePatient**
> CustomApiResponseVoid deletePatient()


### Example

```typescript
import {
    PatientManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new PatientManagementApi(configuration);

let patientId: string; // (default to undefined)

const { status, data } = await apiInstance.deletePatient(
    patientId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **patientId** | [**string**] |  | defaults to undefined|


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

# **filter**
> CustomApiResponsePagePatientResponse filter(searchFilter)


### Example

```typescript
import {
    PatientManagementApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new PatientManagementApi(configuration);

let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filter(
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |


### Return type

**CustomApiResponsePagePatientResponse**

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

# **filterAllergies**
> CustomApiResponsePageAllergyResponse filterAllergies(searchFilter)


### Example

```typescript
import {
    PatientManagementApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new PatientManagementApi(configuration);

let patientId: string; // (default to undefined)
let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filterAllergies(
    patientId,
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |
| **patientId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponsePageAllergyResponse**

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

# **filterEmergencyContacts**
> CustomApiResponsePageEmergencyContactResponse filterEmergencyContacts(searchFilter)


### Example

```typescript
import {
    PatientManagementApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new PatientManagementApi(configuration);

let patientId: string; // (default to undefined)
let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filterEmergencyContacts(
    patientId,
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |
| **patientId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponsePageEmergencyContactResponse**

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

# **getPatientById**
> CustomApiResponsePatientResponse getPatientById()


### Example

```typescript
import {
    PatientManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new PatientManagementApi(configuration);

let patientId: string; // (default to undefined)

const { status, data } = await apiInstance.getPatientById(
    patientId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **patientId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponsePatientResponse**

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

# **updateAllergy**
> CustomApiResponseAllergyResponse updateAllergy(allergyRequest)


### Example

```typescript
import {
    PatientManagementApi,
    Configuration,
    AllergyRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new PatientManagementApi(configuration);

let allergyId: string; // (default to undefined)
let allergyRequest: AllergyRequest; //

const { status, data } = await apiInstance.updateAllergy(
    allergyId,
    allergyRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **allergyRequest** | **AllergyRequest**|  | |
| **allergyId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseAllergyResponse**

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

# **updateEmergencyContact**
> CustomApiResponseEmergencyContactResponse updateEmergencyContact(emergencyContactRequest)


### Example

```typescript
import {
    PatientManagementApi,
    Configuration,
    EmergencyContactRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new PatientManagementApi(configuration);

let contactId: string; // (default to undefined)
let emergencyContactRequest: EmergencyContactRequest; //

const { status, data } = await apiInstance.updateEmergencyContact(
    contactId,
    emergencyContactRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **emergencyContactRequest** | **EmergencyContactRequest**|  | |
| **contactId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseEmergencyContactResponse**

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

# **updatePatient**
> CustomApiResponsePatientResponse updatePatient(patientRequest)


### Example

```typescript
import {
    PatientManagementApi,
    Configuration,
    PatientRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new PatientManagementApi(configuration);

let patientId: string; // (default to undefined)
let patientRequest: PatientRequest; //

const { status, data } = await apiInstance.updatePatient(
    patientId,
    patientRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **patientRequest** | **PatientRequest**|  | |
| **patientId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponsePatientResponse**

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

